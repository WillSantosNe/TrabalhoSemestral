package com.example.trabalhosemestral.repositorio

import com.example.trabalhosemestral.dao.FuncionarioDao
import com.example.trabalhosemestral.entity.Funcionario

class FuncionarioRepositorio(private val funcionarioDao: FuncionarioDao) {

    suspend fun inserirFuncionario(funcionario: Funcionario) {
        funcionarioDao.inserirFuncionario(funcionario)
    }

    suspend fun listarFuncionariosPorTipo(tipo: String): List<Funcionario> {
        return funcionarioDao.listarFuncionariosPorTipo(tipo)
    }
}