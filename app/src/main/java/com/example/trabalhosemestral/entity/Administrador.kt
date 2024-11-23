package com.example.trabalhosemestral.entity

import com.example.trabalhosemestral.data.dao.FuncionarioDao
import com.example.trabalhosemestral.data.entity.Funcionario

class Administrador(private val dao: FuncionarioDao) {

    suspend fun cadastrarFuncionario(usuario: String, senha: String, tipo: String) {

        val novoFuncionario = Funcionario(usuario = usuario, senha = senha, tipo = tipo)
        dao.inserirFuncionario(novoFuncionario)

    }


    suspend fun deletarFuncionario(funcionario: Funcionario) {
        dao.deletarFuncionario(funcionario)
    }

}
