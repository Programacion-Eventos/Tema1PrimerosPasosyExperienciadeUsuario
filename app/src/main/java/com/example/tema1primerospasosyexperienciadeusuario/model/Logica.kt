package com.example.tema1primerospasosyexperienciadeusuario.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import java.util.Calendar

object Logica {

    fun obtenerSaludo(): AnnotatedString {
        val calendario = Calendar.getInstance()
        val hora = calendario.get(Calendar.HOUR_OF_DAY)
        val saludo = when (hora) {
            in 6..11 -> "días"
            in 12..20 -> "tardes"
            else -> "noches"
        }

        return buildAnnotatedString {
            append("Buenas ")
            pushStyle(SpanStyle(color = Color.Yellow)) // Yellow color
            append(saludo)
            pop()
            append(" sr. Profe")
        }
    }
}