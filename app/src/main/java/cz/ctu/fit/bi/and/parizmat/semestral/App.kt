package cz.ctu.fit.bi.and.parizmat.semestral

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import cz.ctu.fit.bi.and.parizmat.semestral.core.di.coreModule
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.di.dictionariesModule
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.di.settingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            modules(coreModule, dictionariesModule, settingsModule)
        }
    }
}
