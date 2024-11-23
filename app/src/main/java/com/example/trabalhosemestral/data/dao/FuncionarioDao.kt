package com.example.trabalhosemestral.data.dao

// Importa as classes necessárias para trabalhar com o Room e a entidade Funcionario
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trabalhosemestral.data.entity.Funcionario

@Dao
interface FuncionarioDao {
    // Inserir um funcionário no banco de dados
    @Insert
    suspend fun inserirFuncionario(funcionario: Funcionario)

    // Listar todos os funcionários
    @Query("SELECT * FROM funcionarios")
    suspend fun listarFuncionarios(): List<Funcionario>

    @Delete
    suspend fun deletarFuncionario(funcionario: Funcionario)
}
