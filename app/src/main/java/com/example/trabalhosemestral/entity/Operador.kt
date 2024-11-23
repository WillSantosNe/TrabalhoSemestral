package com.example.trabalhosemestral.entity

import com.example.trabalhosemestral.data.dao.MaterialDao
import com.example.trabalhosemestral.data.entity.Material

class Operador(private val dao: MaterialDao) {
    suspend fun buscarMaterial(nome: String): Material? {
        return dao.buscarMaterialPorNome(nome)
    }

    suspend fun solicitarMaterial(nome: String, quantidade: Int): Boolean {
        val materialExistente = dao.buscarMaterialPorNome(nome)

        if (materialExistente != null) {
            return if (materialExistente.quantidade >= quantidade) {
                // Decrementa a quantidade e atualiza o material no banco
                val novaQuantidade = materialExistente.quantidade - quantidade
                dao.atualizarMaterial(materialExistente.copy(quantidade = novaQuantidade))

                // Caso a nova quantidade seja 0, exclui o material
                if (novaQuantidade == 0) {
                    dao.deletarMaterial(materialExistente.nome)
                }
                return true
            } else {
                // Quantidade insuficiente, não realiza operação
                return false
            }
        } else {
            // Material não encontrado
            return false
        }
    }
}
