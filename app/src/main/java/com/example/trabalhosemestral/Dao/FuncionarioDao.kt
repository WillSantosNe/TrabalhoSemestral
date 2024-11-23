package com.example.trabalhosemestral.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trabalhosemestral.entity.Funcionario

@Dao
interface FuncionarioDao {
    @Insert
    suspend fun inserirFuncionario(funcionario: Funcionario)

    @Query("SELECT * FROM funcionarios WHERE tipo = :tipo")
    suspend fun listarFuncionariosPorTipo(tipo: String): List<Funcionario>
}