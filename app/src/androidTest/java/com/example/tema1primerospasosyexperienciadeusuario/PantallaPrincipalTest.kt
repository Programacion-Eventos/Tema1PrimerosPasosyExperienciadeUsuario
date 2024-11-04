// PantallaPrincipalTest.kt
package com.example.tema1primerospasosyexperienciadeusuario.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.example.tema1primerospasosyexperienciadeusuario.vista.PantallaPrincipal
import org.junit.Rule
import org.junit.Test

class PantallaPrincipalTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPantallaPrincipal() {
        composeTestRule.setContent {
            PantallaPrincipal(navController = rememberNavController(), backgroundColor = Color.Gray)
        }

        composeTestRule.onNodeWithText("Aquí nombre").performTextInput("Test Name")
        composeTestRule.onNodeWithText("Guarda Nombre").performClick()
        composeTestRule.onNodeWithText("Guardar Nombre: Test Name").assertExists()
    }
}