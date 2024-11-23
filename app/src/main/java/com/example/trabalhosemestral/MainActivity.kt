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
import com.example.trabalhosemestral.data.dao.FuncionarioDao
import com.example.trabalhosemestral.data.dao.MaterialDao
import com.example.trabalhosemestral.data.database.AppDatabase
import com.example.trabalhosemestral.telas.TelaAdmin
import com.example.trabalhosemestral.telas.TelaGerente
import com.example.trabalhosemestral.telas.TelaLogin
import com.example.trabalhosemestral.telas.TelaOperador


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val daoFuncionarios = AppDatabase.getDatabase(applicationContext).funcionarioDao()
            val daoMaterial = AppDatabase.getDatabase(applicationContext).materialDao()
            AppNavigation(daoFuncionarios, daoMaterial)
        }
    }
}

//-------------------------Definindo Nav-----------------
@Composable
fun AppNavigation(daoFuncionarios: FuncionarioDao, daoMaterial : MaterialDao) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { TelaLogin(navController, daoFuncionarios) }
        composable("admin") { TelaAdmin(navController, daoFuncionarios) }
        composable("operador") { TelaOperador(navController, daoMaterial) }
        composable("gerente") { TelaGerente(navController, daoMaterial) }
    }
}