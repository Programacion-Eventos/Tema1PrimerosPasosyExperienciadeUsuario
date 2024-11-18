package com.example.tema1primerospasosyexperienciadeusuario.vista

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tema1primerospasosyexperienciadeusuario.model.Logica
import com.example.tema1primerospasosyexperienciadeusuario.model.Firebase
import com.example.tema1primerospasosyexperienciadeusuario.widgetAdaptado.WidgetNombres

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(navController: NavHostController, backgroundColor: Color) {
    val greeting: AnnotatedString by remember { mutableStateOf(Logica.obtenerSaludo()) }
    val firebaseHelper = Firebase()

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
            Button(
                onClick = { navController.navigate("pantalla_ubicacion") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("IR A UBICACION", fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            WidgetNombres(firebaseHelper = firebaseHelper)
        }
    }
}