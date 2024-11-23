package com.example.trabalhosemestral.telas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabalhosemestral.data.dao.MaterialDao
import com.example.trabalhosemestral.entity.Operador
import kotlinx.coroutines.launch

@Composable
fun TelaOperador(navController: NavController, dao: MaterialDao) {
    val operador = Operador(dao)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Estados para buscar e solicitar material
    var nomeMaterial by remember { mutableStateOf("") }
    var quantidadeMaterial by remember { mutableStateOf("") }
    var resultadoBusca by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo para nome do material
        TextField(
            value = nomeMaterial,
            onValueChange = { nomeMaterial = it },
            label = { Text("Nome do Material") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo para quantidade do material
        TextField(
            value = quantidadeMaterial,
            onValueChange = { quantidadeMaterial = it },
            label = { Text("Quantidade para Solicitar") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botão para buscar material
        Button(onClick = {
            coroutineScope.launch {
                val material = operador.buscarMaterial(nomeMaterial)
                resultadoBusca = if (material != null) {
                    "Material encontrado: Nome: ${material.nome}, Marca: ${material.marca}, Quantidade: ${material.quantidade}"
                } else {
                    "Material não encontrado."
                }
                Toast.makeText(context, resultadoBusca, Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Buscar Material")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Botão para solicitar material
        Button(onClick = {
            coroutineScope.launch {
                if (nomeMaterial.isNotBlank() && quantidadeMaterial.isNotBlank()) {
                    val sucesso = operador.solicitarMaterial(
                        nome = nomeMaterial,
                        quantidade = quantidadeMaterial.toInt()
                    )
                    if (sucesso) {
                        Toast.makeText(
                            context,
                            "Material solicitado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Falha ao solicitar material: Estoque insuficiente ou material inexistente.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Solicitar Material")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibição do resultado da busca
        resultadoBusca?.let {
            Text(text = it, modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para voltar à tela de login
        Button(onClick = {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
        }) {
            Text("Voltar")
        }
    }
}
