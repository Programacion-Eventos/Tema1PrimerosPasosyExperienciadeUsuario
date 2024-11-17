package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DesplegableUbicacionObjetivo(ubicacionObjetivo: String, onUbicacionSeleccionada: (String) -> Unit) {
    val ubicaciones = listOf("Arriba-Izquierda", "Arriba-Derecha", "Abajo-Izquierda", "Abajo-Derecha")
    var expandido by remember { mutableStateOf(false) }

    Box {
        Text(
            text = ubicacionObjetivo,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandido = true }
                .background(Color.LightGray)
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            ubicaciones.forEach { ubicacion ->
                DropdownMenuItem(
                    text = { Text(text = ubicacion) },
                    onClick = {
                        onUbicacionSeleccionada(ubicacion)
                        expandido = false
                    }
                )
            }
        }
    }
}