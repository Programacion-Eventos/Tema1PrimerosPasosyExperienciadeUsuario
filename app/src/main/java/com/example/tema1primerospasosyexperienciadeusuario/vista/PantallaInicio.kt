package com.example.tema1primerospasosyexperienciadeusuario.vista

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.tema1primerospasosyexperienciadeusuario.model.Logica

@Preview(showBackground = true)
@Composable
fun PreviewPantallaInicio() {
    PantallaInicio(navController = rememberNavController(), backgroundColor = Color.Gray)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(navController: NavHostController, backgroundColor: Color) {
    val greeting: AnnotatedString by remember { mutableStateOf(Logica.obtenerSaludo()) }

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