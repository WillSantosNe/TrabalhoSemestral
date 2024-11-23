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
import com.example.trabalhosemestral.data.dao.FuncionarioDao
import com.example.trabalhosemestral.data.entity.Funcionario
import kotlinx.coroutines.launch

@Composable
fun TelaAdmin(navController: NavController, dao: FuncionarioDao) {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Estado para a lista de funcionários
    val funcionarios = remember { mutableStateListOf<Funcionario>() }

    // Carregar lista de funcionários ao iniciar a tela
    LaunchedEffect(Unit) {
        funcionarios.clear()
        funcionarios.addAll(dao.listarFuncionarios())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cadastro de novos funcionários
        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuário") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text("Tipo de Funcionário") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                if (usuario.isNotBlank() && senha.isNotBlank() && tipo.isNotBlank()) {

                    // Se o tipo de funcionário estiver fora dos padrões, aponta erro
                    if(!(tipo in listOf("Gerente", "Operador", "Administrador"))){
                        Toast.makeText(
                            context,
                            "Tipo de Funcionário Inválido!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        dao.inserirFuncionario(
                            Funcionario(
                                usuario = usuario,
                                senha = senha,
                                tipo = tipo
                            )
                        )
                        Toast.makeText(
                            context,
                            "Funcionário cadastrado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()
                        usuario = ""
                        senha = ""
                        tipo = ""

                        // Atualizar a lista de funcionários
                        funcionarios.clear()
                        funcionarios.addAll(dao.listarFuncionarios())
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Preencha todos os campos!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }) {
            Text("Cadastrar Funcionário")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn para listar os funcionários
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(funcionarios) { funcionario ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("ID: ${funcionario.id}, Nome: ${funcionario.usuario}")
                    Button(onClick = {
                        coroutineScope.launch {
                            dao.deletarFuncionario(funcionario)
                            Toast.makeText(
                                context,
                                "Funcionário deletado com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()

                            // Atualizar a lista de funcionários
                            funcionarios.clear()
                            funcionarios.addAll(dao.listarFuncionarios())
                        }
                    }) {
                        Text("Deletar")
                    }
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
