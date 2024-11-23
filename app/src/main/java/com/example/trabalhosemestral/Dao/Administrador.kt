package com.example.trabalhosemestral.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface Administrador{

    @Insert
    suspend fun inserirop(operador: Operador)

    @Insert
    suspend fun inserirge(gerente: Gerente)

    @Insert
    suspend fun inseriradm(administrador: Administrador)

    @Query("SELECT * FROM operador")
    suspend fun buscarTodos(): List<Operador>

    @Query("SELECT * FROM operador WHERE operador = :operador")
    suspend fun buscarPorOperador(operador: String): Operador?

    @Query("DELETE FROM operador WHERE operador = :operador")
    suspend fun deletarConta(operador: String): Int
}
