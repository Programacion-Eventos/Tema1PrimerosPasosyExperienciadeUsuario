package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PantallaUbicacion(navController: NavHostController) {
    var ubicacionActual by remember { mutableStateOf("Arriba-Izquierda") }
    var ubicacionObjetivo by remember { mutableStateOf("Arriba-Izquierda") }
    var colorFondo by remember { mutableStateOf(Color.Transparent) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .padding(16.dp)
    ) {
        Text("Selecciona la ubicación actual:", fontSize = 20.sp)
        SelectorDeUbicacion(ubicacionActual) { ubicacionSeleccionada ->
            ubicacionActual = ubicacionSeleccionada
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Selecciona la ubicación objetivo:", fontSize = 20.sp)
        DesplegableUbicacionObjetivo(ubicacionObjetivo) { ubicacionSeleccionada ->
            ubicacionObjetivo = ubicacionSeleccionada
        }

        Spacer(modifier = Modifier.height(16.dp))

        Brújula(
            ubicacionActual = ubicacionActual,
            ubicacionObjetivo = ubicacionObjetivo,
            onCambioDireccion = { esDireccionCorrecta ->
                colorFondo = when {
                    esDireccionCorrecta == null -> Color.Transparent
                    esDireccionCorrecta -> Color.Green
                    else -> Color.Red
                }
            }
        )
    }
}