package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepperRepository
import org.koin.core.component.KoinComponent

/**
 * Worker class for periodically fetching the current number of steps recorded since the last device reboot
 * and storing them using a repository. This worker is designed to run in the background,
 * even when the app is not active, using the WorkManager API.
 *
 * @param appContext The context of the application used for worker initialization.
 * @param workerParams Parameters for worker configuration and execution.
 * @param repository An instance of StepperRepository for data persistence.
 * @param stepCounter An instance of StepCounterHelper to access the step count.
 */
class StepCounterWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val repository: StepperRepository,
    private val stepCounter: StepCounterHelper
) : CoroutineWorker(appContext, workerParams), KoinComponent {
    /**
     * Executes the work to retrieve and store the step count data. If no steps have been recorded,
     * the work is completed successfully without storing any data.
     *
     * @return Result indicating the outcome of the work; success if steps are retrieved and stored, or if no steps were taken.
     */
    override suspend fun doWork(): Result {
        val stepsSinceLastReboot = stepCounter.steps()
        if (stepsSinceLastReboot == 0L) return Result.success()
        repository.storeSteps(stepsSinceLastReboot)
        return Result.success()
    }
}