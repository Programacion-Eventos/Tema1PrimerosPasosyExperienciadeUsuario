package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun BrújulaVisual(azimut: Float) {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.LightGray)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(150.dp)) {
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(
                    x = center.x + 70 * cos(Math.toRadians(azimut.toDouble() - 90)).toFloat(),
                    y = center.y + 70 * sin(Math.toRadians(azimut.toDouble() - 90)).toFloat()
                ),
                strokeWidth = 4.dp.toPx()
            )
        }
    }
}