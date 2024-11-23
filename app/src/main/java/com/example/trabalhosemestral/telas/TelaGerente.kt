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
fun TelaGerente(navController: NavController) {
    var nomeProduto by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nomeProduto,
            onValueChange = { nomeProduto = it },
            label = { Text("Nome do Produto") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            println("Cadastrando produto: $nomeProduto")
        }) {
            Text("Cadastrar Produto")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            println("Listando produtos...")
        }) {
            Text("Listar Produtos")
        }
    }
}