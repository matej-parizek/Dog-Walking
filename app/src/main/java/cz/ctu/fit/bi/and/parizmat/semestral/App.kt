package cz.ctu.fit.bi.and.parizmat.semestral

import android.app.Application
import androidx.work.Configuration
import com.google.firebase.crashlytics.FirebaseCrashlytics
import cz.ctu.fit.bi.and.parizmat.semestral.core.di.coreModule
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.di.dictionariesModule
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.di.settingsModule
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.di.stepperModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            workManagerFactory()
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            modules(coreModule, dictionariesModule, settingsModule, stepperModule)
        }
    }
}
