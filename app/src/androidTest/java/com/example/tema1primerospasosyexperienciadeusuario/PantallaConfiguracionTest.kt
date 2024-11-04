package com.example.tema1primerospasosyexperienciadeusuario.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.tema1primerospasosyexperienciadeusuario.vista.PantallaConfiguracion
import org.junit.Rule
import org.junit.Test

class PantallaConfiguracionTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPantallaConfiguracion() {
        composeTestRule.setContent {
            PantallaConfiguracion(navController = rememberNavController(), backgroundColor = Color.Gray, onColorChange = {})
        }

        composeTestRule.onNodeWithText("Rojo").performClick()
        composeTestRule.onNodeWithText("Volver a la Pantalla de Inicio").performClick()
    }
}