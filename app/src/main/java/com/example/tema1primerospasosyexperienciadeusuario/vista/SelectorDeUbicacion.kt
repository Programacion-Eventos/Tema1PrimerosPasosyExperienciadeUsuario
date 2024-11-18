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
fun SelectorDeUbicacionUnificado(
    ubicacionActual: Pair<Int, Int>,
    ubicacionObjetivo: Pair<Int, Int>,
    turnoSeleccion: Boolean,
    onUbicacionSeleccionada: (Pair<Int, Int>, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.Gray)
    ) {
        for (y in 4 downTo -4) {
            Row(modifier = Modifier.weight(1f)) {
                for (x in -7..7) {
                    val ubicacion = Pair(x, y)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(
                                when {
                                    ubicacion == ubicacionActual -> Color.Blue
                                    ubicacion == ubicacionObjetivo -> Color.Red
                                    else -> Color.White
                                }
                            )
                            .clickable { onUbicacionSeleccionada(ubicacion, turnoSeleccion) }
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
