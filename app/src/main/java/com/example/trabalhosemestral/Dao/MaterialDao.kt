package com.example.trabalhosemestral.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trabalhosemestral.entity.Material

@Dao
interface MaterialDao {

    // Adiciona materiais na nossa lista
    @Insert
    suspend fun inserirMaterial(material: Material)

    // Buscar todos os materiais já listados
    @Query("SELECT * FROM materiais")
    suspend fun listarMaterial(): List<Material>
}
