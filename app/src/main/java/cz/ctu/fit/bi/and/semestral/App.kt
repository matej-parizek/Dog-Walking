package cz.ctu.fit.bi.and.semestral

import android.app.Application
import cz.ctu.fit.bi.and.semestral.core.di.coreModule
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.di.dictionariesModule
import cz.ctu.fit.bi.and.semestral.feature.settings.di.settingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(coreModule, dictionariesModule, settingsModule)
        }
    }
}
