package com.example.lab08.ui.theme.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab08.TaskViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NoteAppScaffold() {
    Scaffold(
        topBar = { AppTopBar() },
        floatingActionButton = { AddNoteFab() },
        content = { padding ->
            NoteContent(Modifier.padding(padding))
        },
        bottomBar = { AppBottomBar() }
    )
}



@Composable
fun AddNoteFab(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { /* Acción para agregar nota */ },
        containerColor = Color(0xFF7D5260)
    ) {
        Row (modifier = modifier
            .padding(16.dp)) {
            Icon(Icons.Default.Add, contentDescription = "Agregar Nota")
            Text(text = "Agregar nota")
        }
    }
}

@Composable
fun NoteContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Aquí agregas la lógica para mostrar la lista de notas
        Text("Aquí van las notas")
    }
}



@Preview(showBackground = true)
@Composable
fun NoteAppPreview() {
    NoteAppScaffold()
}
