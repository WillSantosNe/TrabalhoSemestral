package com.example.trabalhosemestral.telas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.trabalhosemestral.data.entity.Material
import com.example.trabalhosemestral.entity.Gerente
import kotlinx.coroutines.launch

@Composable
fun TelaGerente(navController: NavController, dao: MaterialDao) {
    val gerente = Gerente(dao)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Estados para cadastrar material
    var nome by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }

    // Estado para lista de materiais
    val materiais = remember { mutableStateListOf<Material>() }

    // Função para carregar a lista de materiais
    LaunchedEffect(Unit) {
        materiais.clear()
        materiais.addAll(gerente.listarMaterial())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cadastro de material
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Material") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text("Marca do Material") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                if (nome.isNotBlank() && marca.isNotBlank() && quantidade.isNotBlank()) {
                    gerente.cadastrarMaterial(
                        nome = nome,
                        marca = marca,
                        quantidade = quantidade.toInt()
                    )
                    Toast.makeText(
                        context,
                        "Material cadastrado ou atualizado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                    nome = ""
                    marca = ""
                    quantidade = ""

                    // Atualizar lista de materiais
                    materiais.clear()
                    materiais.addAll(gerente.listarMaterial())
                } else {
                    Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Cadastrar Material")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Listagem de materiais
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(materiais) { material ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Nome: ${material.nome}, Marca: ${material.marca}, Quantidade: ${material.quantidade}")
                }
            }
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
