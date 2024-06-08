package cz.ctu.fit.bi.and.parizmat.semestral

import android.app.Application
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.crashlytics.FirebaseCrashlytics
import cz.ctu.fit.bi.and.parizmat.semestral.core.di.coreModule
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.di.dictionariesModule
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.di.settingsModule
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.di.stepperModule
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase.StepCounterWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.factory.KoinWorkerFactory
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.GlobalContext.startKoin
import java.util.concurrent.TimeUnit

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
//            workManagerFactory()
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            modules(coreModule, dictionariesModule, settingsModule, stepperModule)
        }

        val myWork = PeriodicWorkRequestBuilder<StepCounterWorker>(
            15, TimeUnit.MINUTES).build()

        // Inicializace WorkManager s KoinWorkerFactory
        val config = Configuration.Builder()
            .setWorkerFactory(KoinWorkerFactory())
            .build()
        WorkManager.initialize(this,config)
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("MyUniqueWorkName",
                ExistingPeriodicWorkPolicy.UPDATE, myWork)
//            .initialize(this@App, config)

    }
}
