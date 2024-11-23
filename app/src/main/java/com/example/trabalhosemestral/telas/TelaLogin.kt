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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabalhosemestral.data.dao.FuncionarioDao
import com.example.trabalhosemestral.data.entity.Funcionario

@Composable
fun TelaLogin(navController: NavController, dao: FuncionarioDao) {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val context = LocalContext.current
    val funcionarios = remember { mutableStateListOf<Funcionario>() }

    // Carregar funcionários do banco na primeira execução
    LaunchedEffect(Unit) {
        val funcionariosBanco = dao.listarFuncionarios()
        if (funcionariosBanco.isEmpty()) {
            // Inserir administrador inicial
            val admin = Funcionario(usuario = "admin", senha = "admin123", tipo = "Administrador")
            dao.inserirFuncionario(admin)
            funcionarios.add(admin)
        } else {
            funcionarios.addAll(funcionariosBanco)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Verificar credenciais no banco de dados
            val funcionario = funcionarios.find { it.usuario == usuario && it.senha == senha }
            if (funcionario != null) {
                when (funcionario.tipo) {
                    "Administrador" -> navController.navigate("admin")
                    "Operador" -> navController.navigate("operador")
                    "Gerente" -> navController.navigate("gerente")
                }
            } else {
                Toast.makeText(context, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Entrar")
        }
    }
}


suspend fun autenticarUsuario(usuario: String, senha: String, funcionarios: List<Funcionario>): Funcionario? {
    return funcionarios.find { it.usuario == usuario && it.senha == senha }
}

