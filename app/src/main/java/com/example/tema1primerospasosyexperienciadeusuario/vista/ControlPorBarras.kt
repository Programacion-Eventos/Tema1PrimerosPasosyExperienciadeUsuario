package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ControlPorBarras(
    ubicacionActual: Pair<Int, Int>,
    onUbicacionCambiada: (Pair<Int, Int>) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Control de ubicación actual", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))

        // Barra para controlar X
        Text("Eje X: ${ubicacionActual.first}", fontSize = 16.sp)
        Slider(
            value = ubicacionActual.first.toFloat(),
            onValueChange = { x ->
                onUbicacionCambiada(Pair(x.toInt(), ubicacionActual.second))
            },
            valueRange = -7f..7f,
            steps = 14, // Para valores discretos
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Barra para controlar Y
        Text("Eje Y: ${ubicacionActual.second}", fontSize = 16.sp)
        Slider(
            value = ubicacionActual.second.toFloat(),
            onValueChange = { y ->
                onUbicacionCambiada(Pair(ubicacionActual.first, y.toInt()))
            },
            valueRange = -4f..4f,
            steps = 8, // Para valores discretos
            modifier = Modifier.fillMaxWidth()
        )
    }
}
