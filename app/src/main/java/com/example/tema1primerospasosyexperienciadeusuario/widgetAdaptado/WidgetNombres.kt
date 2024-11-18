package com.example.tema1primerospasosyexperienciadeusuario.widgetAdaptado

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.example.tema1primerospasosyexperienciadeusuario.model.Firebase

@Composable
fun WidgetNombres(firebaseHelper: Firebase) {
    var expanded by remember { mutableStateOf(false) }
    var nombres by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(Unit) {
        firebaseHelper.getNombres(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val nombresList = snapshot.children.mapNotNull { it.getValue(String::class.java) }
                nombres = nombresList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable { expanded = !expanded }
    ) {
        Text(
            text = "Nombres Guardados (Click)",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            nombres.forEach { nombre ->
                Text(
                    text = "- $nombre",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}