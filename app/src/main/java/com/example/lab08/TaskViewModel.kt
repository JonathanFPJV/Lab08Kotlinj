package com.example.lab08

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val dao: TaskDao): ViewModel(){
    // Estado para la lista de tareas
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks


    init {
        // Al inicializar, cargamos las tareas de la base de datos
        viewModelScope.launch {
            _tasks.value = dao.getAllTasks()
        }
    }


    // Función para añadir una nueva tarea
    fun addTask(description: String, category: String, priority: Int) {
        val newTask = Task(description = description,category = category, priority = priority)
        viewModelScope.launch {
            dao.insertTask(newTask)
            _tasks.value = dao.getAllTasks() // Recargamos la lista
        }
    }


    // Función para alternar el estado de completado de una tarea
    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            val updatedTask = task.copy(isCompleted = !task.isCompleted)
            dao.updateTask(updatedTask)
            _tasks.value = dao.getAllTasks() // Recargamos la lista
        }
    }


    // Función para eliminar todas las tareas
    fun deleteAllTasks() {
        viewModelScope.launch {
            dao.deleteAllTasks()
            _tasks.value = emptyList() // Vaciamos la lista en el estado
        }
    }

    // Función para filtrar tareas
    fun filterTasks(status: String, category: String, priority: Int) {
        viewModelScope.launch {
            val filteredTasks = dao.getAllTasks().filter { task ->
                (status == "" || task.isCompleted == (status == "completa")) &&
                        (category == "" || task.category == category) &&
                        (priority == -1 || task.priority == priority)
            }
            _tasks.value = filteredTasks
        }
    }
    fun getAllTasks() {
        viewModelScope.launch {
            _tasks.value = dao.getAllTasks()
        }
    }

}