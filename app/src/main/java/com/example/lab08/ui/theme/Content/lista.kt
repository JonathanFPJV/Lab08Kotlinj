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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
                TaskItem(task = task, onToggleCompletion = {
                    viewModel.toggleTaskCompletion(task) // Cambiar el estado de completado
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { coroutineScope.launch { viewModel.deleteAllTasks() } },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7D5260)
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
