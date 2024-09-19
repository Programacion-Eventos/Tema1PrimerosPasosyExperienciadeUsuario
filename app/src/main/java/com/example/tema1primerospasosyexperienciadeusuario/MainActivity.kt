package com.example.tema1primerospasosyexperienciadeusuario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tema1primerospasosyexperienciadeusuario.ui.theme.PantallaInicio
import com.example.tema1primerospasosyexperienciadeusuario.ui.theme.PantallaPrincipal
import com.example.tema1primerospasosyexperienciadeusuario.ui.theme.PantallaConfiguracion
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    var backgroundColor by remember { mutableStateOf(Color.Gray) }

    NavHost(navController = navController, startDestination = "pantalla_inicio") {
        composable("pantalla_inicio") { PantallaInicio(navController, backgroundColor) }
        composable("pantalla_principal") { PantallaPrincipal(navController, backgroundColor) }
        composable("pantalla_configuracion") { PantallaConfiguracion(navController, backgroundColor) { newColor ->
            backgroundColor = newColor
        }}
    }
}