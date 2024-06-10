package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import kotlinx.coroutines.suspendCancellableCoroutine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.resume

/**
 * This class provides a helper to manage step counting using the device's hardware sensors.
 * It handles sensor registration and captures step count data asynchronously when the device's step counter sensor updates.
 *
 * Initialization includes setting up the sensor service and registering the step counter sensor if available.
 */

class StepCounterHelper(
) : KoinComponent {
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    private val context: Context by inject()

    /**
     * Initializes the sensor manager and attempts to register the step counter sensor.
     */
    init {
        createChannel()
    }

    /**
     * Asynchronously retrieves the current step count using the device's step counter sensor.
     * The step count returned is the number of steps taken since the last device reboot.
     *
     * @return A suspension point that resumes with the step count when sensor data is available.
     */
    suspend fun steps() = suspendCancellableCoroutine { continuation ->
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event == null) return

                val stepsSinceLastReboot = event.values[0].toLong()

                if (continuation.isActive) {
                    continuation.resume(stepsSinceLastReboot)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }
        sensorManager.registerListener(
            listener,
            sensor, SensorManager.SENSOR_DELAY_UI
        )
    }

    /**
     * Configures the sensor and sensor manager. This method is specifically designed to operate
     * on Android Q and newer, where permissions and sensor behavior might vary.
     */
    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            sensorManager =
                context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        }

    }
}