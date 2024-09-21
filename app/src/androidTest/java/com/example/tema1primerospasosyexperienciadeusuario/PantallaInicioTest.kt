package com.example.tema1primerospasosyexperienciadeusuario.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class PantallaInicioTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPantallaInicio() {
        composeTestRule.setContent {
            PantallaInicio(navController = rememberNavController(), backgroundColor = Color.Gray)
        }

        composeTestRule.onNodeWithText("IR A PRINCIPAL").assertExists().performClick()
    }
}