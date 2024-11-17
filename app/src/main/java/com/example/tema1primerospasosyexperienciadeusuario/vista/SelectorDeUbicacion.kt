package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectorDeUbicacion(ubicacionActual: String, onUbicacionSeleccionada: (String) -> Unit) {
    val ubicaciones = listOf("Arriba-Izquierda", "Arriba-Derecha", "Abajo-Izquierda", "Abajo-Derecha")
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.Gray)
    ) {
        ubicaciones.forEach { ubicacion ->
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(if (ubicacionActual == ubicacion) Color.Blue else Color.White)
                    .clickable { onUbicacionSeleccionada(ubicacion) }
                    .align(
                        when (ubicacion) {
                            "Arriba-Izquierda" -> Alignment.TopStart
                            "Arriba-Derecha" -> Alignment.TopEnd
                            "Abajo-Izquierda" -> Alignment.BottomStart
                            "Abajo-Derecha" -> Alignment.BottomEnd
                            else -> Alignment.Center
                        }
                    )
            )
        }
    }
}