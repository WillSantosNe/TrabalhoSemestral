package com.example.trabalhosemestral.telas

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.trabalhosemestral.entity.Operador
import com.example.trabalhosemestral.repositorio.FuncionarioRepositorio
import androidx.compose.runtime.LaunchedEffect

@Composable
fun TelaOperador(navController: NavController, repositorio: FuncionarioRepositorio) {

    // Tela para cadastrar contas
    val operador = Operador(1)

    // Usar LaunchedEffect para chamar a função suspend
    LaunchedEffect(Unit) {
        repositorio.inserirFuncionario(operador)
    }
}