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
import com.example.trabalhosemestral.entity.Funcionario

@Composable
fun TelaLogin(navController: NavController) {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val context = LocalContext.current

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
            val funcionario = autenticarUsuario(usuario, senha)
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

// Função para autenticar usuários
fun autenticarUsuario(usuario: String, senha: String): Funcionario? {
    return when (usuario) {
        "admin" -> Funcionario(usuario = "admin", senha = "123", tipo = "Administrador")
        "operador" -> Funcionario(usuario = "operador", senha = "123", tipo = "Operador")
        "gerente" -> Funcionario(usuario = "gerente", senha = "123", tipo = "Gerente")
        else -> null
    }
}
