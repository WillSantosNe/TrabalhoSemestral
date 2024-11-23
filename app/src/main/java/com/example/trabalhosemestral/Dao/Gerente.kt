package com.example.trabalhosemestral.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Gerente{
    @Insert
    suspend fun Adicionar(material: Material)

    @Query("SELECT * FROM mateial")
    suspend fun buscarTodosr(): List<Material>
}
