package com.example.tema1primerospasosyexperienciadeusuario.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController




@Preview(showBackground = true)
@Composable
fun PreviewPantallaPrincipal() {
    PantallaPrincipal(navController = rememberNavController(), backgroundColor = Color.Gray)
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(navController: NavHostController, backgroundColor: Color) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var savedName by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Pantalla Principal", fontSize = 42.sp, fontWeight = FontWeight.Bold) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .background(backgroundColor)
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Aquí nombre", fontWeight = FontWeight.Light) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(Color.Yellow)
                    .border(2.dp, Color.Black)
            )
            Button(
                onClick = { savedName = name.text },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guarda Nombre", fontSize = 30.sp)
            }
            if (savedName.isNotEmpty()) {
                Text(
                    text = "Guardar Nombre: $savedName",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            Button(
                onClick = { navController.navigate("pantalla_configuracion") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Ir Config.", fontSize = 30.sp)
            }
        }
    }
}