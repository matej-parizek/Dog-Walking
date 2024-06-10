package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local

import android.content.ContentValues.TAG
import android.util.Log
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.DataError
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Response
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.handleExceptionDatabase
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.dao.SettingDao
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.dao.StepCountDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class StepperRepository(
    private val stepsDao: StepCountDao,
    private val settingDao: SettingDao
) {

    /**
     * Stores the number of steps since the last reboot into the database.
     * This is executed in the background thread to avoid blocking the UI.
     *
     * @param stepsSinceLastReboot The steps counted since the last reboot.
     */
    suspend fun storeSteps(stepsSinceLastReboot: Long) = withContext(Dispatchers.IO) {
        val stepCount = StepCountEntity(
            steps = stepsSinceLastReboot,
            createdAt = Instant.now().toString()
        )
        stepsDao.insertAll(stepCount)
    }
    /**
     * Loads the steps recorded for the current day and calculates the difference between the first and last data point.
     *
     * @return A Response object containing either the calculated steps or an error.
     */
    suspend fun loadTodaySteps(): Response<Long, DataError> {
        return try {

            val todaySteps = withContext(Dispatchers.IO) {
                val todayAtMidnight =
                    (LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toString())
                val todayDataPoints =
                    stepsDao.loadAllStepsFromToday(startDateTime = todayAtMidnight)
                when {
                    todayDataPoints.isEmpty() -> 0
                    else -> {
                        val firstDataPointOfTheDay = todayDataPoints.first()
                        val latestDataPointSoFar = todayDataPoints.last()

                        val todaySteps = latestDataPointSoFar.steps - firstDataPointOfTheDay.steps
                        Log.d(TAG, "Today Steps: $todaySteps")
                        todaySteps
                    }
                }
            }
            Response.Success(todaySteps)
        } catch (e: Exception) {
            handleExceptionDatabase(e)
        }
    }

    /**
     * Loads all steps data, groups them by date, and calculates the total steps for each day.
     *
     * @return A Response object containing a pair of lists for steps and corresponding days, or an error.
     */
    suspend fun loadSteps(): Response<Pair<List<Float>,List<String>>, DataError> {
        return try {
            val result = stepsDao.loadAllSteps().groupBy {
                val result = it.createdAt.substringBefore('T')
                result
            }
            val stepsPerDay = result.map {
                val max = it.value.maxBy { steps -> steps.steps }
                val min = it.value.minBy { steps -> steps.steps }
                (max.steps - min.steps).toFloat()
            }
            val day = result.map { it.key }
            Response.Success(Pair(stepsPerDay, day))
        } catch (e: Exception) {
            handleExceptionDatabase(e)
        }
    }

    /**
     * Retrieves person settings from the settings database for person.
     *
     * @return LiveData or an object containing the settings data for a person.
     */
    fun settingsPerson() = settingDao.get(0)

    /**
     * Retrieves dog settings from the settings database for dog.
     *
     * @return LiveData or an object containing the settings data for a dog.
     */
    fun settingsDog() = settingDao.get(1)
}

