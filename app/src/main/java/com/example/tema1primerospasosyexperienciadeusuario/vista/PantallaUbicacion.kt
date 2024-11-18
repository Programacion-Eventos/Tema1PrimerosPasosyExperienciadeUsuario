package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PantallaUbicacion() {
    var ubicacionActual by remember { mutableStateOf(Pair(0, 0)) }
    var ubicacionObjetivo by remember { mutableStateOf(Pair(3, 2)) } // Ejemplo de objetivo inicial
    var colorFondo by remember { mutableStateOf(Color.White) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .padding(16.dp)
    ) {
        ControlPorBarras(
            ubicacionActual = ubicacionActual,
            onUbicacionCambiada = { nuevaUbicacion ->
                ubicacionActual = nuevaUbicacion
            }
        )

        Movimiento(
            ubicacionActual = ubicacionActual,
            ubicacionObjetivo = ubicacionObjetivo,
            onCambioDireccion = { direccion ->
                colorFondo = when (direccion) {
                    true -> Color.Green
                    false -> Color.Red
                    else -> Color.White
                }
            }
        )

        Text(
            text = "Ubicación actual: ${ubicacionActual.first}, ${ubicacionActual.second}",
            fontSize = 16.sp
        )
        Text(
            text = "Ubicación objetivo: ${ubicacionObjetivo.first}, ${ubicacionObjetivo.second}",
            fontSize = 16.sp
        )
    }
}
