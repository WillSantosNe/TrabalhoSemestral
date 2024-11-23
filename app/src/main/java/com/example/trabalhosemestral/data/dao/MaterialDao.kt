package com.example.trabalhosemestral.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.trabalhosemestral.data.entity.Material

@Dao
interface MaterialDao {

    // Adiciona materiais na nossa lista
    @Insert
    suspend fun inserirMaterial(material: Material)

    // Buscar material pelo nome
    @Query("SELECT * FROM materiais WHERE nome = :nome")
    suspend fun buscarMaterialPorNome(nome: String): Material?

    // Atualizar material
    @Update
    suspend fun atualizarMaterial(material: Material)


    // Buscar todos os materiais já listados
    @Query("SELECT * FROM materiais")
    suspend fun listarMaterial(): List<Material>


    @Query("DELETE FROM materiais WHERE nome = :nome")
    suspend fun deletarMaterial(nome: String)
}
