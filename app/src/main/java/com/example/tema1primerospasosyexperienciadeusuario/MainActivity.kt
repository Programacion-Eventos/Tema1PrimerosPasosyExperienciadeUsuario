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
import com.example.tema1primerospasosyexperienciadeusuario.model.*
import com.example.tema1primerospasosyexperienciadeusuario.vista.PantallaConfiguracion
import com.example.tema1primerospasosyexperienciadeusuario.vista.PantallaInicio
import com.example.tema1primerospasosyexperienciadeusuario.vista.PantallaPrincipal
import com.example.tema1primerospasosyexperienciadeusuario.vista.PantallaUbicacion
import java.io.IOException


class MainActivity : ComponentActivity() {
    private lateinit var preferencesManager: PreferenceManager
    private lateinit var sqliteHelper: SQLite
    private lateinit var firebaseHelper: Firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesManager = PreferenceManager(this)
        sqliteHelper = SQLite(this)
        firebaseHelper = Firebase()

        // Example usage of database export/import to internal storage
        try {
            sqliteHelper.exportarBaseDatosAAlmacenamientoInterno(this)
            sqliteHelper.importarBaseDatosDesdeAlmacenamientoInterno(this)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        setContent {
            MyApp(
                preferencesManager = preferencesManager,
                sqliteHelper = sqliteHelper,
                firebaseHelper = firebaseHelper
            )
        }
    }
}

@Composable
fun MyApp(
    preferencesManager: PreferenceManager,
    sqliteHelper: SQLite,
    firebaseHelper: Firebase
) {
    val navController = rememberNavController()
    var userName by remember { mutableStateOf(preferencesManager.userName) }
    var backgroundColor by remember { mutableStateOf(Color(preferencesManager.getBackgroundColor(userName))) }

    NavHost(navController = navController, startDestination = "pantalla_inicio") {
        composable("pantalla_inicio") { PantallaInicio(navController, backgroundColor) }
        composable("pantalla_principal") {
            PantallaPrincipal(
                navController = navController,
                backgroundColor = backgroundColor,
                preferencesManager = preferencesManager,
                onSave = { newName: String, newColor: Color ->
                    userName = newName
                    backgroundColor = newColor
                    preferencesManager.saveUserNameAndColor(newName, newColor.toArgb())
                },
                sqliteHelper = sqliteHelper,
                firebaseHelper = firebaseHelper
            )
        }
        composable("pantalla_configuracion") {
            PantallaConfiguracion(navController, backgroundColor) { newColor: Color ->
                backgroundColor = newColor
                preferencesManager.saveUserNameAndColor(userName, newColor.toArgb())
            }
        }
        composable("pantalla_ubicacion") { PantallaUbicacion(navController) }
    }
}