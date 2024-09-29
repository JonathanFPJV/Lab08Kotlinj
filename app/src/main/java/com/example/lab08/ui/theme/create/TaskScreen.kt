package com.example.lab08.ui.theme.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab08.TaskViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var newTaskDescription by remember { mutableStateOf("") }
    var newTaskCategory by remember { mutableStateOf("") }
    var newTaskPriority by remember { mutableStateOf(0) }  // Mantén esto para la prioridad seleccionada
    var showDatePicker by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = newTaskDescription,
            onValueChange = { newTaskDescription = it },
            label = { Text("Nueva tarea") },
            modifier = Modifier.fillMaxWidth()
        )
        // Campo de categoría
        TextField(
            value = newTaskCategory,
            onValueChange = { newTaskCategory = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        // Grupo de RadioButtons para Prioridad
        Text("Prioridad")
        Row {
            RadioButton(
                selected = newTaskPriority == 0,
                onClick = { newTaskPriority = 0 }
            )
            Text(text = "Baja", modifier = Modifier.padding(start = 8.dp))

            RadioButton(
                selected = newTaskPriority == 1,
                onClick = { newTaskPriority = 1 }
            )
            Text(text = "Media", modifier = Modifier.padding(start = 8.dp))

            RadioButton(
                selected = newTaskPriority == 2,
                onClick = { newTaskPriority = 2 }
            )
            Text(text = "Alta", modifier = Modifier.padding(start = 8.dp))
        }





        Button(
            onClick = {
                if (newTaskDescription.isNotEmpty()) {
                    viewModel.addTask(newTaskDescription, newTaskCategory, newTaskPriority)
                    newTaskDescription = ""
                    newTaskCategory = ""
                    newTaskPriority = 0
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Agregar tarea")
        }


        Spacer(modifier = Modifier.height(16.dp))


        tasks.forEach { task ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task.description)
                Button(onClick = { viewModel.toggleTaskCompletion(task) }) {
                    Text(if (task.isCompleted) "Completada" else "Pendiente")
                }
            }
        }


        Button(
            onClick = { coroutineScope.launch { viewModel.deleteAllTasks() } },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Eliminar todas las tareas")
        }
    }
}
