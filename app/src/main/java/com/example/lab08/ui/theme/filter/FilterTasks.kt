package com.example.lab08.ui.theme.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    onDismiss: () -> Unit,
    onApplyFilters: (String, String, Int) -> Unit // Ahora incluye también la prioridad
) {
    var selectedStatus by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf(0) }  // Estado para la prioridad

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Filtros", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))

            // Estado
            Text("Estado:", style = MaterialTheme.typography.labelSmall)
            Row {
                RadioButton(
                    selected = selectedStatus == "completa",
                    onClick = { selectedStatus = "completa" }
                )
                Text("Completa")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = selectedStatus == "incompleta",
                    onClick = { selectedStatus = "incompleta" }
                )
                Text("Incompleta")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Prioridad
            Text("Prioridad:", style = MaterialTheme.typography.labelSmall)
            Row {
                RadioButton(
                    selected = selectedPriority == 0,
                    onClick = { selectedPriority = 0 }
                )
                Text(text = "Baja", modifier = Modifier.padding(start = 8.dp))

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = selectedPriority == 1,
                    onClick = { selectedPriority = 1 }
                )
                Text(text = "Media", modifier = Modifier.padding(start = 8.dp))

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = selectedPriority == 2,
                    onClick = { selectedPriority = 2 }
                )
                Text(text = "Alta", modifier = Modifier.padding(start = 8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onApplyFilters(selectedStatus, selectedDate, selectedPriority)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7D5260)
                ),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Aplicar filtros")
            }
        }
    }
}
