package com.example.trabalhosemestral.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Operador{

    @Query("SELECT * FROM material WHERE nome LIKE :nome")
    suspend fun buscarMaterialPorNome(nome: String): Material?

}
