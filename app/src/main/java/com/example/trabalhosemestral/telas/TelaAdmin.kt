package com.example.trabalhosemestral.telas

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.trabalhosemestral.entity.Administrador
import com.example.trabalhosemestral.repositorio.FuncionarioRepositorio
import androidx.compose.runtime.LaunchedEffect

@Composable
fun TelaAdmin(navController: NavController, repositorio: FuncionarioRepositorio) {

    // Tela para cadastrar contas
    val admin = Administrador(1)

    // Usar LaunchedEffect para chamar a função suspend
    LaunchedEffect(Unit) {
        repositorio.inserirFuncionario(admin)
    }
}