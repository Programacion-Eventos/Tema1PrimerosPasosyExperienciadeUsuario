package com.example.tema1primerospasosyexperienciadeusuario.vista

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

@Composable
fun Brújula(
    ubicacionActual: String,
    ubicacionObjetivo: String,
    onCambioDireccion: (Boolean?) -> Unit
) {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val accelerometer = remember { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    val magnetometer = remember { sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) }

    var azimut by remember { mutableStateOf(0f) }
    var esDireccionCorrecta by remember { mutableStateOf<Boolean?>(null) }

    val sensorEventListener = remember {
        object : SensorEventListener {
            private val gravedad = FloatArray(3)
            private val geomagnetico = FloatArray(3)

            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    when (it.sensor.type) {
                        Sensor.TYPE_ACCELEROMETER -> {
                            System.arraycopy(it.values, 0, gravedad, 0, it.values.size)
                        }
                        Sensor.TYPE_MAGNETIC_FIELD -> {
                            System.arraycopy(it.values, 0, geomagnetico, 0, it.values.size)
                        }
                    }

                    val R = FloatArray(9)
                    val I = FloatArray(9)
                    if (SensorManager.getRotationMatrix(R, I, gravedad, geomagnetico)) {
                        val orientacion = FloatArray(3)
                        SensorManager.getOrientation(R, orientacion)
                        azimut = Math.toDegrees(orientacion[0].toDouble()).toFloat()
                        esDireccionCorrecta = comprobarDireccion(ubicacionActual, ubicacionObjetivo, azimut)
                        onCambioDireccion(esDireccionCorrecta)
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
    }

    DisposableEffect(Unit) {
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(sensorEventListener, magnetometer, SensorManager.SENSOR_DELAY_UI)
        onDispose {
            sensorManager.unregisterListener(sensorEventListener)
        }
    }

    Text("Azimut: $azimut", fontSize = 20.sp)
}

fun comprobarDireccion(ubicacionActual: String, ubicacionObjetivo: String, azimut: Float): Boolean? {
    return when (ubicacionActual) {
        "Arriba-Izquierda" -> when (ubicacionObjetivo) {
            "Arriba-Derecha" -> azimut in 45f..135f
            "Abajo-Izquierda" -> azimut in 135f..225f
            "Abajo-Derecha" -> azimut in 45f..225f
            else -> null
        }
        "Arriba-Derecha" -> when (ubicacionObjetivo) {
            "Arriba-Izquierda" -> azimut in 225f..315f
            "Abajo-Derecha" -> azimut in 135f..225f
            "Abajo-Izquierda" -> azimut in 225f..315f
            else -> null
        }
        "Abajo-Izquierda" -> when (ubicacionObjetivo) {
            "Arriba-Izquierda" -> azimut in 315f..45f
            "Abajo-Derecha" -> azimut in 45f..135f
            "Arriba-Derecha" -> azimut in 315f..135f
            else -> null
        }
        "Abajo-Derecha" -> when (ubicacionObjetivo) {
            "Arriba-Derecha" -> azimut in 315f..45f
            "Abajo-Izquierda" -> azimut in 225f..315f
            "Arriba-Izquierda" -> azimut in 225f..45f
            else -> null
        }
        else -> null
    }
}