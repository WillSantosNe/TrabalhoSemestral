package com.example.trabalhosemestral.repositorio

import com.example.trabalhosemestral.dao.FuncionarioDao
import com.example.trabalhosemestral.entity.Funcionario

// Ela é responsável por separar a lógica de acesso ao banco de dados da lógica de apresentação ou de uso da aplicação.

class FuncionarioRepositorio(private val funcionarioDao: FuncionarioDao) {

    // Função para inserir um novo funcionário no banco de dados.
    // Ela recebe um objeto Funcionario como parâmetro e chama o método `inserirFuncionario` do DAO para inserir o funcionário na tabela.
    suspend fun inserirFuncionario(funcionario: Funcionario) {
        // Chama o método do DAO para realizar a operação de inserção no banco de dados.
        funcionarioDao.inserirFuncionario(funcionario)
    }

    // Função para listar todos os funcionários de um tipo específico.
    // Ela recebe uma string `tipo` como parâmetro (que pode ser "admin", "operador", etc.).
    // Ela chama o método `listarFuncionariosPorTipo` do DAO para recuperar a lista de funcionários filtrada por tipo.
    suspend fun listarFuncionariosPorTipo(tipo: String): List<Funcionario> {
        // Chama o método do DAO para buscar os funcionários no banco de dados e retorna a lista
        return funcionarioDao.listarFuncionariosPorTipo(tipo)
    }
}
