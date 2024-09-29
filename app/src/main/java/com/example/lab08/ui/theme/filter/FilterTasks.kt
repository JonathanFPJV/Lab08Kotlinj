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
    onApplyFilters: (String, String) -> Unit
) {
    var selectedStatus by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

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

            // Fecha
            Text("Fecha:", style = MaterialTheme.typography.labelSmall)
            // Aquí podrías implementar un selector de fecha. Por simplicidad, usaremos un TextField
            TextField(
                value = selectedDate,
                onValueChange = { selectedDate = it },
                label = { Text("Seleccionar fecha") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onApplyFilters(selectedStatus, selectedDate) },
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
