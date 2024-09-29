package com.example.lab08.ui.theme.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {

    TopAppBar(
        title = {
            Text(
                text = "Listado de Tareas",
                color = Color.White, // Texto en color blanco
                style = MaterialTheme.typography.titleLarge // Estilo de texto
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF7D5260),  // Color de fondo
            titleContentColor = Color.White, // Color del texto del título
            actionIconContentColor = Color.White // Color de los íconos
        ),
        actions = {
            IconButton(onClick = { /* Acción de búsqueda */ }) {
                Icon(Icons.Default.Search, contentDescription = "Buscar notas")
            }
            IconButton(onClick = { /* Acción de notificaciones */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
            }
        }
    )
}
