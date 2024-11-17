package com.example.tema1primerospasosyexperienciadeusuario.vista

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Movimiento(
    ubicacionActual: Pair<Int, Int>,
    ubicacionObjetivo: Pair<Int, Int>,
    onCambioDireccion: (Boolean?) -> Unit,
    onMovementChange: (Boolean, Pair<Int, Int>) -> Unit,
    onAzimutChange: (Float) -> Unit
) {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val accelerometer = remember { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    val magnetometer = remember { sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) }

    var lastX by remember { mutableStateOf(0f) }
    var lastY by remember { mutableStateOf(0f) }
    var currentLocation by remember { mutableStateOf(ubicacionActual) }
    var previousDistance by remember { mutableStateOf(calculateDistance(ubicacionActual, ubicacionObjetivo)) }
    val coroutineScope = rememberCoroutineScope()

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
                        val azimut = Math.toDegrees(orientacion[0].toDouble()).toFloat()
                        onAzimutChange(azimut)

                        val currentX = gravedad[0]
                        val currentY = gravedad[1]
                        val isMoving = currentX != lastX || currentY != lastY
                        if (isMoving) {
                            currentLocation = Pair(
                                (currentLocation.first + (currentX - lastX).toInt()).coerceIn(-7, 7),
                                (currentLocation.second + (currentY - lastY).toInt()).coerceIn(-4, 4)
                            )
                        }
                        onMovementChange(isMoving, currentLocation)
                        lastX = currentX
                        lastY = currentY

                        val newDistance = calculateDistance(currentLocation, ubicacionObjetivo)
                        if (newDistance < previousDistance) {
                            coroutineScope.launch {
                                onCambioDireccion(true)
                                delay(1000)
                                onCambioDireccion(null)
                            }
                        } else if (newDistance > previousDistance) {
                            coroutineScope.launch {
                                onCambioDireccion(false)
                                delay(1000)
                                onCambioDireccion(null)
                            }
                        }
                        previousDistance = newDistance
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
}

fun calculateDistance(ubicacionActual: Pair<Int, Int>, ubicacionObjetivo: Pair<Int, Int>): Double {
    val (xActual, yActual) = ubicacionActual
    val (xObjetivo, yObjetivo) = ubicacionObjetivo
    return Math.sqrt(Math.pow((xObjetivo - xActual).toDouble(), 2.0) + Math.pow((yObjetivo - yActual).toDouble(), 2.0))
}