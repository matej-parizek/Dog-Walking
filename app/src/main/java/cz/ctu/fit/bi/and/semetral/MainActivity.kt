package cz.ctu.fit.bi.and.semetral

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.ctu.fit.bi.and.semetral.core.ui.MainScreen
import cz.ctu.fit.bi.and.semetral.feature.settings.ui.SettingsScreen
import cz.ctu.fit.bi.and.semetral.feature.stepper.ui.stats.BarCharScreen
import cz.ctu.fit.bi.and.semetral.ui.theme.BIANDSEMETRALTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BIANDSEMETRALTheme {
                MainScreen()
            }
        }
    }
}