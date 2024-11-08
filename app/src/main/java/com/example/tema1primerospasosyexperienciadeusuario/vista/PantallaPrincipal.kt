package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tema1primerospasosyexperienciadeusuario.model.Firebase
import com.example.tema1primerospasosyexperienciadeusuario.model.PreferenceManager
import com.example.tema1primerospasosyexperienciadeusuario.model.SQLite
import com.example.tema1primerospasosyexperienciadeusuario.model.SegundoPlano

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(
    navController: NavHostController,
    backgroundColor: Color,
    preferencesManager: PreferenceManager,
    onSave: (String, Color) -> Unit,
    sqliteHelper: SQLite,
    firebaseHelper: Firebase
) {
    var nombre by remember { mutableStateOf(TextFieldValue(preferencesManager.getUserName() ?: "")) }
    var nombreGuardado by remember { mutableStateOf(preferencesManager.getUserName() ?: "") }
    var progreso by remember { mutableStateOf(0) }
    var tareaEnEjecucion by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Pantalla Principal", fontSize = 42.sp, fontWeight = FontWeight.Bold) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .background(backgroundColor)
        ) {
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Aquí nombre", fontWeight = FontWeight.Light) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(Color.Yellow)
                    .border(2.dp, Color.Black)
            )
            Button(
                onClick = {
                    nombreGuardado = nombre.text
                    onSave(nombreGuardado, backgroundColor)
                    // Guardar en SQLite
                    sqliteHelper.insertarNombre(nombreGuardado)
                    // Guardar en Firebase
                    firebaseHelper.saveNombre(nombreGuardado)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guarda Nombre", fontSize = 30.sp)
            }
            if (nombreGuardado.isNotEmpty()) {
                Text(
                    text = "Guardar Nombre: $nombreGuardado",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            Button(
                onClick = { navController.navigate("pantalla_configuracion") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Ir Config.", fontSize = 30.sp)
            }
            Button(
                onClick = {
                    if (!tareaEnEjecucion) {
                        tareaEnEjecucion = true
                        SegundoPlano(
                            actualizarProgreso = { progreso = it },
                            tareaFinalizada = { tareaEnEjecucion = false }
                        ).execute()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Iniciar Tarea en Segundo Plano", fontSize = 30.sp)
            }
            if (tareaEnEjecucion) {
                LinearProgressIndicator(progress = progreso / 100f, modifier = Modifier.fillMaxWidth().padding(top = 16.dp))
                Text("Progreso: $progreso%", modifier = Modifier.padding(top = 8.dp))
            }
            Button(
                onClick = {
                    sqliteHelper.borrarTodosLosNombres()
                    firebaseHelper.borrarTodosLosNombres()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Borrar Todos los Nombres", fontSize = 30.sp)
            }
        }
    }
}