package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.di

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.core.content.ContextCompat.getSystemService
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.WalkingDogDatabase
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase.StepCounterWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val stepperModule = module {
    //Sensor and managerSensor
    single { androidContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    factory { get<SensorManager>().getDefaultSensor(Sensor.TYPE_STEP_COUNTER) }

    single { get<WalkingDogDatabase>().stepCountDao() }

    worker { StepCounterWorker(appContext = androidContext(), workerParams = get(), repository = get(), stepCounter = get()) }

}