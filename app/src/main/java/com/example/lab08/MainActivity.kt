package com.example.lab08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import kotlinx.coroutines.launch
import com.example.lab08.ui.theme.Lab08Theme
import com.example.lab08.ui.theme.create.TaskScreen
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab08Theme {
                // Inicializamos la base de datos con la opción de realizar migraciones destructivas
                val db = Room.databaseBuilder(
                    applicationContext,
                    TaskDatabase::class.java,
                    "task_db"
                )
                    .fallbackToDestructiveMigration() // Elimina la base de datos anterior si hay cambios en la versión
                    .build()

                // Obtenemos el DAO para interactuar con la base de datos
                val taskDao = db.taskDao()

                // Pasamos el DAO al ViewModel
                val viewModel = TaskViewModel(taskDao)

                // Pasamos el ViewModel a la pantalla
                TaskScreen(viewModel)
            }
        }
    }
}


