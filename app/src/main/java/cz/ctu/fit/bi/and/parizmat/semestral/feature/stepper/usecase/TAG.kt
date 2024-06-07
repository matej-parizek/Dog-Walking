package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

private const val TAG = "STEP_COUNT_LISTENER"

class StepCounter(
    private val sensorManager: SensorManager?,
    private val sensor: Sensor?
) {

    suspend fun steps() = suspendCancellableCoroutine { continuation ->
        Log.d(TAG, "Registering sensor listener... ")

        val listener: SensorEventListener by lazy {
            object : SensorEventListener {
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
        }

        val supportedAndEnabled = sensorManager?.registerListener(
            listener,
            sensor, SensorManager.SENSOR_DELAY_UI
        )
        Log.d(TAG, "Sensor listener registered: $supportedAndEnabled")
    }
}