package com.example.trabalhosemestral.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TelaOperador(navController: NavController) {
    var nomePeca by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nomePeca,
            onValueChange = { nomePeca = it },
            label = { Text("Nome da Peça") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            println("Buscando peça: $nomePeca")
        }) {
            Text("Buscar Peça")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            println("Solicitando peça: $nomePeca")
        }) {
            Text("Solicitar Peça")
        }
    }
}