package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun SelectorDeUbicacion(
    ubicacionActual: Pair<Int, Int>,
    onUbicacionSeleccionada: (Pair<Int, Int>) -> Unit,
    modifier: Modifier = Modifier
) {
    val ubicaciones = (-7..7).flatMap { x ->
        (-4..4).map { y ->
            Pair(x, y)
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        for (y in -4..4) {
            Row(modifier = Modifier.weight(1f)) {
                for (x in -7..7) {
                    val ubicacion = Pair(x, y)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(if (ubicacionActual == ubicacion) Color.Blue else Color.White)
                            .clickable { onUbicacionSeleccionada(ubicacion) }
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawRect(
                                color = Color.Black,
                                style = Stroke(width = 1.dp.toPx())
                            )
                        }
                    }
                }
            }
        }
    }
}