package com.example.lab08.ui.theme.Content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab08.Task
import com.example.lab08.TaskViewModel
import kotlinx.coroutines.launch

@Composable
fun NoteContent(padding: PaddingValues, modifier: Modifier = Modifier, viewModel: TaskViewModel) {
    // Obtener las tareas desde el ViewModel
    val tasks by viewModel.tasks.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        // Usar LazyColumn para mostrar la lista de tareas
        LazyColumn {
            items(tasks) { task ->
                TaskCard(
                    task = task,
                    onDelete = {
                        viewModel.deleteTask(task)  // Eliminar la tarea cuando se presiona el botón
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { coroutineScope.launch { viewModel.deleteAllTasks() } },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD78183)
            ),
        ) {
            Text("Eliminar todas las tareas")
        }
    }
}

@Composable
fun TaskItem(task: Task, onToggleCompletion: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = task.description, color = Color.White)
        Button(onClick = onToggleCompletion) {
            Text(if (task.isCompleted) "Completada" else "Pendiente")
        }
    }
}

@Composable
fun TaskCard(task: Task, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Espacio alrededor de la tarjeta
        shape = RoundedCornerShape(16.dp), // Forma redondeada
        colors = CardDefaults.cardColors(containerColor = Color(0xFF725049)) // Color de fondo de la tarjeta
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Padding interno de la tarjeta
            verticalAlignment = Alignment.CenterVertically, // Alinear verticalmente
            horizontalArrangement = Arrangement.SpaceBetween // Separación de los elementos
        ) {
            // Columna para el contenido de la tarea (Descripción y Prioridad)
            Column(
                modifier = Modifier.weight(1f), // Ocupa la mayor parte del espacio
                verticalArrangement = Arrangement.Center
            ) {
                // Descripción de la tarea como título
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Prioridad de la tarea en texto más pequeño
                Text(
                    text = when (task.priority) {
                        0 -> "Prioridad: Baja"
                        1 -> "Prioridad: Media"
                        2 -> "Prioridad: Alta"
                        else -> "Prioridad: Desconocida"
                    },
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray) // Texto más pequeño y en gris claro
                )
            }

            // Botón de icono de tacho de basura para eliminar la tarea
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete, // Icono de eliminar
                    contentDescription = "Eliminar tarea",
                    tint = Color.White // Color del icono
                )
            }
        }
    }
}
