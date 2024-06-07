package cz.ctu.fit.bi.and.parizmat.semestral

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.hardware.SensorManager
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.MainScreen
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.BIANDSEMETRALTheme

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