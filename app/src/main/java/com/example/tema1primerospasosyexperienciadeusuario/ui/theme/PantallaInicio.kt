package com.example.tema1primerospasosyexperienciadeusuario.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavHostController
import java.util.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController




@Preview(showBackground = true)
@Composable
fun PreviewPantallaInicio() {
    PantallaInicio(navController = rememberNavController(), backgroundColor = Color.Gray)
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(navController: NavHostController, backgroundColor: Color) {
    val greeting by remember { mutableStateOf(getGreeting()) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Pantalla de Inicio", fontSize = 40.sp, fontWeight = FontWeight.Bold) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .background(backgroundColor)
        ) {
            Text(
                text = greeting,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { navController.navigate("pantalla_principal") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("IR A PRINCIPAL", fontSize = 30.sp)
            }
        }
    }
}

fun getGreeting(): AnnotatedString {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val greeting = when (hour) {
        in 6..11 -> "días"
        in 12..20 -> "tardes"
        else -> "noches"
    }
    return buildAnnotatedString {
        append("¡¡Buenas ")
        withStyle(style = SpanStyle(color = Color.Yellow)) {
            append(greeting)
        }
        append(" Sr.Profe!!")
    }
}