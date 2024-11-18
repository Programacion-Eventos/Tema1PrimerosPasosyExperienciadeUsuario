package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.runtime.*

@Composable
fun Movimiento(
    ubicacionActual: Pair<Int, Int>,
    ubicacionObjetivo: Pair<Int, Int>,
    onCambioDireccion: (Boolean?) -> Unit
) {
    var previousDistance by remember { mutableStateOf(calculateDistance(ubicacionActual, ubicacionObjetivo)) }

    // Recalcula la distancia cuando cambian las ubicaciones
    LaunchedEffect(ubicacionActual, ubicacionObjetivo) {
        val nuevaDistancia = calculateDistance(ubicacionActual, ubicacionObjetivo)
        println("Ubicación actual: $ubicacionActual, Ubicación objetivo: $ubicacionObjetivo")
        println("Distancia previa: $previousDistance, Distancia nueva: $nuevaDistancia")

        if (nuevaDistancia != previousDistance) {
            when {
                nuevaDistancia < previousDistance -> {
                    println("Dirección: Acercándose (verde)")
                    onCambioDireccion(true) // Verde
                }
                nuevaDistancia > previousDistance -> {
                    println("Dirección: Alejándose (rojo)")
                    onCambioDireccion(false) // Rojo
                }
                else -> {
                    println("Dirección: Neutral (blanco)")
                    onCambioDireccion(null) // Blanco
                }
            }
        }

        previousDistance = nuevaDistancia
    }
}


fun calculateDistance(ubicacionActual: Pair<Int, Int>, ubicacionObjetivo: Pair<Int, Int>): Double {
    val (xActual, yActual) = ubicacionActual
    val (xObjetivo, yObjetivo) = ubicacionObjetivo
    return Math.sqrt(Math.pow((xObjetivo - xActual).toDouble(), 2.0) + Math.pow((yObjetivo - yActual).toDouble(), 2.0))
}