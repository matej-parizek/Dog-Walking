package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.di

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.WalkingDogDatabase
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepperRepository
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stats.StatsViewModel
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stepper.StepperViewModel
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase.StepCounterHelper
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase.StepCounterWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val stepperModule = module {
    //Sensor and managerSensor

    single { get<WalkingDogDatabase>().stepCountDao() }
    single { StepperRepository(stepsDao = get(), settingDao = get()) }
    single { StepCounterHelper() }
    worker {
        StepCounterWorker(
            appContext = androidContext(),
            workerParams = get(),
            repository = get(),
            stepCounter = get()
        )
    }
    viewModel { StepperViewModel(repository = get(), stepCounter = get()) }
    viewModel { StatsViewModel(repository = get()) }
}