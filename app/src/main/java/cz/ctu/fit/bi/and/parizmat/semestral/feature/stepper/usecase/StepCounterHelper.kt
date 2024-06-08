package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import kotlinx.coroutines.suspendCancellableCoroutine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.resume

private const val TAG = "STEP_COUNT_LISTENER"

class StepCounter(
) : KoinComponent {
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    private var supportedAndEnabled: Boolean = false
    private val context: Context by inject()

    init {
        createChannel()
    }

    suspend fun steps() = suspendCancellableCoroutine { continuation ->
//        Log.d(TAG, "Registering sensor listener... ")
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event == null) return

                val stepsSinceLastReboot = event.values[0].toLong()
                Log.d(TAG, "Steps since last reboot: $stepsSinceLastReboot")

                if (continuation.isActive) {
                    continuation.resume(stepsSinceLastReboot)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                Log.d(TAG, "Accuracy changed to: $accuracy")
            }
        }
        supportedAndEnabled = sensorManager.registerListener(
            listener,
            sensor, SensorManager.SENSOR_DELAY_UI
        )
    }


    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            sensorManager =
                context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        }

    }
}