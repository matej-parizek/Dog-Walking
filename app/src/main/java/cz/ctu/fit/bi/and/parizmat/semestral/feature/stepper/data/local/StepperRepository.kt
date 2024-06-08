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

    suspend fun storeSteps(stepsSinceLastReboot: Long) = withContext(Dispatchers.IO) {
        val stepCount = StepCountEntity(
            steps = stepsSinceLastReboot,
            createdAt = Instant.now().toString()
        )
        stepsDao.insertAll(stepCount)
    }

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

    suspend fun loadSteps(): Response<List<Float>, DataError> {
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
            Response.Success(stepsPerDay)
        } catch (e: Exception) {
            handleExceptionDatabase(e)
        }
    }

    fun settingsPerson() = settingDao.get(0)
    fun settingsDog() = settingDao.get(1)
}

