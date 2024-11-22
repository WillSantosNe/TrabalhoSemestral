package com.example.trabalhosemestral

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

//-------------------------Definindo Nav-----------------

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { Login(navController) }
        composable("operador/{username}") { backStackEntry ->
            Operador(navController, backStackEntry.arguments?.getString("username"))
        }
        composable("adminstrador/{username}") { backStackEntry ->
            Administrador(navController, backStackEntry.arguments?.getString("username"))
        }
        composable("gerente/{username}") { backStackEntry ->
            Gerente(navController, backStackEntry.arguments?.getString("username"))
        }
    }
}

//-----------------Login------------------------------

@Composable
fun Login(navController: NavController) {

    // Estados para armazenar login e senha
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Layout da tela de login
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuário") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        /*Button(onClick = { //Logica})
            {
                Text("Entrar")
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }
        }*/
    }
}

//------------------------ Operador ---------------------------------
@Composable
fun Operador(navController: NavController, username: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bem-vindo, $username!")
        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            }
        ) {
            Text("Voltar para Login")
        }
    }
}


//----------------------------ADM -----------------------------

@Composable
fun Administrador(navController: NavController, username: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bem-vindo, $username!")
        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            }
        ) {
            Text("Voltar para Login")
        }
    }
}


//------------------------------gerente ------------------------

@Composable
fun Gerente(navController: NavController, username: String?) {

    var nome by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    //var material by remember { mutableStateOf(listOf<Material>()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bem-vindo, $username!", modifier = Modifier.fillMaxWidth(),
            fontSize = 22.sp, textAlign = TextAlign.Center)

        Text(text = "Cadastra produto", modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(10.dp))

        //Campo para nomme
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            value = nome, onValueChange = { nome = it },
            label = { Text(text = "Nome do produto")})

        // Campo de texto para Marca
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
                value = marca, onValueChange = { marca = it},
            label = { Text(text = "Marca")})

        // Campo de texto para Quantidade
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
                value = marca, onValueChange = { quantidade= it},
            label = { Text(text = "Quantidade")})

        Button(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
            onClick = {
                if (nome.isNotBlank() && marca.isNotBlank() && quantidade.isNotBlank()) {
                    //material = material + Material(nome, marca, quantidade)
                    nome = ""  // Limpar o campo de nome
                    marca = ""  // Limpar o campo de marca
                    quantidade = "" //Limpa o campo de quantidadde
                }else {
                    Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }
            }) {
            Text(text = "Salvar produto")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Proutos Cadastrados", modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp)


        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            }
        ) {
            Text("Voltar para Login")
        }
    }
}
    //------------------------- Database MaterialItem -------------------------