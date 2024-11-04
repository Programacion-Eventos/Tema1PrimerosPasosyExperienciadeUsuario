package com.example.tema1primerospasosyexperienciadeusuario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tema1primerospasosyexperienciadeusuario.model.PreferenceManager
import com.example.tema1primerospasosyexperienciadeusuario.vista.PantallaConfiguracion
import com.example.tema1primerospasosyexperienciadeusuario.vista.PantallaInicio
import com.example.tema1primerospasosyexperienciadeusuario.vista.PantallaPrincipal

class MainActivity : ComponentActivity() {
    private lateinit var preferencesManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesManager = PreferenceManager(this)
        setContent {
            MyApp(preferencesManager)
        }
    }
}

@Composable
fun MyApp(preferencesManager: PreferenceManager) {
    val navController = rememberNavController()
    var userName by remember { mutableStateOf(preferencesManager.getUserName()) }
    var backgroundColor by remember { mutableStateOf(Color(preferencesManager.getBackgroundColor(userName))) }

    NavHost(navController = navController, startDestination = "pantalla_inicio") {
        composable("pantalla_inicio") { PantallaInicio(navController, backgroundColor) }
        composable("pantalla_principal") { PantallaPrincipal(navController, backgroundColor, preferencesManager) { newName, newColor ->
            userName = newName
            backgroundColor = newColor
            preferencesManager.saveUserNameAndColor(newName, newColor.toArgb())
        }}
        composable("pantalla_configuracion") { PantallaConfiguracion(navController, backgroundColor) { newColor ->
            backgroundColor = newColor
            preferencesManager.saveUserNameAndColor(userName, newColor.toArgb())
        }}
    }
}