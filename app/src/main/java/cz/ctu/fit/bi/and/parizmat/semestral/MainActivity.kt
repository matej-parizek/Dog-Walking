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
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val  sensorManager: SensorManager by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BIANDSEMETRALTheme {
                MainScreen()
            }
        }
    }

    override fun onPause() {
        super.onPause()
//        sensorManager.unregisterListener(this)
    }
}