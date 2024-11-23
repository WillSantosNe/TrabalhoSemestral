package com.example.trabalhosemestral.telas

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.trabalhosemestral.entity.Gerente
import com.example.trabalhosemestral.entity.Material
import com.example.trabalhosemestral.repositorio.FuncionarioRepositorio
import androidx.compose.runtime.LaunchedEffect

@Composable
fun TelaGerente(navController: NavController, repositorio: FuncionarioRepositorio) {

    // Tela para cadastrar contas
    val gerente = Gerente(1)

    // Usar LaunchedEffect para chamar a função suspend
    LaunchedEffect(Unit) {
        repositorio.inserirMaterial(gerente)
    }
}