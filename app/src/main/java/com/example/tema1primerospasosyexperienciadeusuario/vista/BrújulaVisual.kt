package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp


@Composable
fun BrújulaVisualNoFuncional(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = modifier
            .background(Color.LightGray)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(150.dp)) {
            drawCircle(
                color = Color.Black,
                radius = size.minDimension / 2,
                style = Stroke(width = 4.dp.toPx())
            )
            rotate(rotation) {
                drawLine(
                    color = Color.Red,
                    start = center,
                    end = Offset(
                        x = center.x,
                        y = center.y - size.minDimension / 2 + 16.dp.toPx()
                    ),
                    strokeWidth = 4.dp.toPx()
                )
            }
        }
    }
}