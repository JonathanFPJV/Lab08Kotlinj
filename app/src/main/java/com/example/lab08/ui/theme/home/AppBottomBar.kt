package com.example.lab08.ui.theme.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AppBottomBar() {
    BottomAppBar(
        content = {
            // Ordenar
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { /* Acción de ordenar */ }) {
                    Icon(Icons.Default.Sort, contentDescription = "Ordenar")
                }
                Text(text = "Ordenar", style = MaterialTheme.typography.labelSmall)
            }

            Spacer(Modifier.weight(1f, true))

            // Filtrar
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { /* Acción de filtrar */ }) {
                    Icon(Icons.Default.FilterList, contentDescription = "Filtrar")
                }
                Text(text = "Filtrar", style = MaterialTheme.typography.labelSmall)
            }

            Spacer(Modifier.weight(1f, true))

            // Compartir
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { /* Acción de compartir */ }) {
                    Icon(Icons.Default.Share, contentDescription = "Compartir")
                }
                Text(text = "Compartir", style = MaterialTheme.typography.labelSmall)
            }

            Spacer(Modifier.weight(1f, true))

            // Configuración
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { /* Acción de configuración */ }) {
                    Icon(Icons.Default.Settings, contentDescription = "Configuración")
                }
                Text(text = "Config.", style = MaterialTheme.typography.labelSmall)
            }
        }
    )
}
