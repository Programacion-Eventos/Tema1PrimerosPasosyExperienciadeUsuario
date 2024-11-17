package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CoordenadasSelector(
    ubicacionActual: Pair<Int, Int>,
    onUbicacionChange: (Pair<Int, Int>) -> Unit
) {
    var x by remember { mutableStateOf(ubicacionActual.first) }
    var y by remember { mutableStateOf(ubicacionActual.second) }

    LaunchedEffect(ubicacionActual) {
        x = ubicacionActual.first
        y = ubicacionActual.second
    }

    Column {
        Text("Coordenada X: $x")
        Slider(
            value = x.toFloat(),
            onValueChange = { newX ->
                x = newX.toInt()
                onUbicacionChange(Pair(x, y))
            },
            valueRange = -7f..7f,
            modifier = Modifier.padding(16.dp)
        )

        Text("Coordenada Y: $y")
        Slider(
            value = y.toFloat(),
            onValueChange = { newY ->
                y = newY.toInt()
                onUbicacionChange(Pair(x, y))
            },
            valueRange = -4f..4f, // Correct the range
            modifier = Modifier.padding(16.dp)
        )
    }
}