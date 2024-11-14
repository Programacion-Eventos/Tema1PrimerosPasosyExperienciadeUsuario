package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

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
                .fillMaxSize()
        ) {
            NombreTextFieldAndSaveButton(
                nombre = nombre,
                onNombreChange = { nombre = it },
                backgroundColor = backgroundColor,
                onSave = { newName, newColor ->
                    nombreGuardado = newName
                    onSave(newName, newColor)
                },
                sqliteHelper = sqliteHelper,
                firebaseHelper = firebaseHelper
            )
            SavedNameText(nombreGuardado = nombreGuardado)
            NavigationButton(navController = navController)
            BackgroundTaskButtonAndProgressIndicator(
                tareaEnEjecucion = tareaEnEjecucion,
                progreso = progreso,
                onStartTask = {
                    tareaEnEjecucion = true
                    SegundoPlano(
                        actualizarProgreso = { progreso = it },
                        tareaFinalizada = { tareaEnEjecucion = false }
                    ).execute()
                }
            )
            DeleteButton(sqliteHelper = sqliteHelper, firebaseHelper = firebaseHelper)
            NombresPanel(firebaseHelper = firebaseHelper)
        }
    }
}

@Composable
fun NombreTextFieldAndSaveButton(
    nombre: TextFieldValue,
    onNombreChange: (TextFieldValue) -> Unit,
    backgroundColor: Color,
    onSave: (String, Color) -> Unit,
    sqliteHelper: SQLite,
    firebaseHelper: Firebase
) {
    Column {
        TextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Aquí nombre", fontWeight = FontWeight.Light) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color.Yellow)
                .border(2.dp, Color.Black)
        )
        Button(
            onClick = {
                onSave(nombre.text, backgroundColor)
                sqliteHelper.insertarNombre(nombre.text)
                firebaseHelper.saveNombre(nombre.text)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guarda Nombre", fontSize = 30.sp)
        }
    }
}

@Composable
fun BackgroundTaskButtonAndProgressIndicator(
    tareaEnEjecucion: Boolean,
    progreso: Int,
    onStartTask: () -> Unit
) {
    Column {
        Button(
            onClick = { if (!tareaEnEjecucion) onStartTask() },
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
    }
}

@Composable
fun SavedNameText(nombreGuardado: String) {
    if (nombreGuardado.isNotEmpty()) {
        Text(
            text = "Guardar Nombre: $nombreGuardado",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun NavigationButton(navController: NavHostController) {
    Button(
        onClick = { navController.navigate("pantalla_configuracion") },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text("Ir Config.", fontSize = 30.sp)
    }
}

@Composable
fun DeleteButton(sqliteHelper: SQLite, firebaseHelper: Firebase) {
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

@Composable
fun NombresPanel(firebaseHelper: Firebase) {
    var nombres by remember { mutableStateOf(listOf<String>()) }
    var isExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        firebaseHelper.getNombres(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val nombresList = snapshot.children.mapNotNull { it.getValue(String::class.java) }
                nombres = nombresList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Nombres",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(bottom = 8.dp)
        )
        AnimatedVisibility(visible = isExpanded) {
            Column {
                nombres.forEach { nombre ->
                    Text(
                        text = "- $nombre",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                }
            }
        }
    }
}