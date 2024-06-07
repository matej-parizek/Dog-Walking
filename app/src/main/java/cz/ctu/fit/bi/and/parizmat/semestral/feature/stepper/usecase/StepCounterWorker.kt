package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepperRepository
import org.koin.core.component.KoinComponent

private const val TAG = " StepCounterWorker"
class StepCounterWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val repository: StepperRepository,
    private val stepCounter: StepCounter
) : CoroutineWorker(appContext, workerParams), KoinComponent{
    override suspend fun doWork(): Result {
        Log.d(TAG, "Starting worker...")

        val stepsSinceLastReboot = stepCounter.steps()
        if (stepsSinceLastReboot == 0L) return Result.success()

        Log.d(TAG, "Received steps from step sensor: $stepsSinceLastReboot")
        repository.storeSteps(stepsSinceLastReboot)

        Log.d(TAG, "Stopping worker...")
        return Result.success()
    }
}