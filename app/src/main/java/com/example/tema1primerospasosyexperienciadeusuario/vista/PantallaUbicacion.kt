package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment


@Composable
fun PantallaUbicacion(navController: NavController) {
    var ubicacionActual by remember { mutableStateOf(Pair(0, 0)) }
    var ubicacionObjetivo by remember { mutableStateOf(Pair(3, 2)) } // Ejemplo de objetivo inicial
    var colorFondo by remember { mutableStateOf(Color.White) }
    var turnoSeleccion by remember { mutableStateOf(true) } // true para ubicacionActual, false para ubicacionObjetivo

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
            color = Color.Blue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Ubicación objetivo: ${ubicacionObjetivo.first}, ${ubicacionObjetivo.second}",
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        SelectorDeUbicacionUnificado(
            ubicacionActual = ubicacionActual,
            ubicacionObjetivo = ubicacionObjetivo,
            turnoSeleccion = turnoSeleccion,
            onUbicacionSeleccionada = { nuevaUbicacion, turno ->
                if (turno) {
                    ubicacionActual = nuevaUbicacion
                } else {
                    ubicacionObjetivo = nuevaUbicacion
                }
                turnoSeleccion = !turnoSeleccion // Cambiar el turno
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp)
                .weight(1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        BrújulaVisualNoFuncional(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp)
                .weight(1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("pantalla_inicio") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Volver a la pantalla de inicio")
        }
    }
}