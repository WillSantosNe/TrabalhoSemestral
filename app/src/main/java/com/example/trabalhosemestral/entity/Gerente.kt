package com.example.trabalhosemestral.entity

import com.example.trabalhosemestral.data.dao.MaterialDao
import com.example.trabalhosemestral.data.entity.Material

class Gerente (private val dao: MaterialDao){

    suspend fun cadastrarMaterial(nome: String, marca: String, quantidade: Int) {
        val materialExistente = dao.buscarMaterialPorNome(nome)

        if (materialExistente != null) {
            // Atualiza a quantidade do material existente
            val novaQuantidade = materialExistente.quantidade + quantidade
            dao.atualizarMaterial(materialExistente.copy(quantidade = novaQuantidade))
        } else {
            // Insere um novo material
            dao.inserirMaterial(Material(nome = nome, marca = marca, quantidade = quantidade))
        }
    }


    suspend fun listarMaterial() : List<Material> {
        return dao.listarMaterial()
    }
}