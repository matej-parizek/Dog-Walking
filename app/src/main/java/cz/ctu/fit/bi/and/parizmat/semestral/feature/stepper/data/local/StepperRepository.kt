package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local

import android.content.ContentValues.TAG
import android.util.Log
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.dao.StepCountDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class StepperRepository(
    private val stepsDao: StepCountDao,
) {

    suspend fun storeSteps(stepsSinceLastReboot: Long) = withContext(Dispatchers.IO) {
        val stepCount = StepCountEntity(
            steps = stepsSinceLastReboot,
            createdAt = Instant.now().toString()
        )
        Log.d(TAG, "Storing steps: $stepCount")
        stepsDao.insertAll(stepCount)
    }

    suspend fun loadTodaySteps(): Long = withContext(Dispatchers.IO) {

        val todayAtMidnight = (LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toString())
        val todayDataPoints = stepsDao.loadAllStepsFromToday(startDateTime = todayAtMidnight)
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
}

