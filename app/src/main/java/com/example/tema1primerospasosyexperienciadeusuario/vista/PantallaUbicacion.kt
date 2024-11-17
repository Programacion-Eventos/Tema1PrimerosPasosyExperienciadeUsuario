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
    var ubicacionActual by remember { mutableStateOf(Pair(0, 0)) }
    var ubicacionObjetivo by remember { mutableStateOf(Pair(0, 0)) }
    var colorFondo by remember { mutableStateOf(Color.White) }
    var isMoving by remember { mutableStateOf(false) }
    var azimut by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .padding(16.dp)
    ) {
        Text("Selecciona la ubicación actual:", fontSize = 20.sp)
        CoordenadasSelector(
            ubicacionActual = ubicacionActual,
            onUbicacionChange = { nuevaUbicacion ->
                ubicacionActual = nuevaUbicacion
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            SelectorDeUbicacion(
                ubicacionActual = ubicacionActual,
                onUbicacionSeleccionada = { ubicacionSeleccionada ->
                    ubicacionActual = ubicacionSeleccionada
                },
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            SelectorDeUbicacion(
                ubicacionActual = ubicacionObjetivo,
                onUbicacionSeleccionada = { ubicacionSeleccionada ->
                    ubicacionObjetivo = ubicacionSeleccionada
                },
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Movimiento(
            ubicacionActual = ubicacionActual,
            ubicacionObjetivo = ubicacionObjetivo,
            onCambioDireccion = { esDireccionCorrecta ->
                colorFondo = when {
                    esDireccionCorrecta == null -> Color.White
                    esDireccionCorrecta -> Color.Green
                    else -> Color.Red
                }
            },
            onMovementChange = { moving, newLocation ->
                isMoving = moving
                if (moving) {
                    ubicacionActual = newLocation
                }
            },
            onAzimutChange = { newAzimut ->
                azimut = newAzimut
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Posición actual: (${ubicacionActual.first}, ${ubicacionActual.second})", fontSize = 20.sp)
        Text("Posición deseada: (${ubicacionObjetivo.first}, ${ubicacionObjetivo.second})", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        BrújulaVisual(azimut = azimut)
    }
}