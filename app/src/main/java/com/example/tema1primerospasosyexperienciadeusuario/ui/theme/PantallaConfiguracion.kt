package com.example.tema1primerospasosyexperienciadeusuario.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Preview(showBackground = true)
@Composable
fun PreviewPantallaConfiguracion() {
    PantallaConfiguracion(navController = rememberNavController(), backgroundColor = Color.Gray, onColorChange = {})
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConfiguracion(navController: NavHostController, backgroundColor: Color, onColorChange: (Color) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Pantalla de Configuración", fontSize = 32.sp, fontWeight = FontWeight.Bold) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Text(
                text = "Selecciona el color de fondo:",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = { onColorChange(Color.Red) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Rojo", fontSize = 30.sp)
            }
            Button(
                onClick = { onColorChange(Color.Green) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Verde", fontSize = 30.sp)
            }
            Button(
                onClick = { onColorChange(Color.Blue) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Azul", fontSize = 30.sp)
            }
            Button(
                onClick = { navController.navigate("pantalla_inicio") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Volver a la Pantalla de Inicio", fontSize = 20.sp)
            }
        }
    }
}