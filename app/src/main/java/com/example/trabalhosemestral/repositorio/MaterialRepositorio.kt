package com.example.trabalhosemestral.repositorio

import com.example.trabalhosemestral.dao.MaterialDao
import com.example.trabalhosemestral.entity.Material

class MaterialRepositorio(private val materialDao: MaterialDao) {

   suspend fun inserirMaterial(material: Material) {
        materialDao.inserirMaterial(material)
    }

    suspend fun listarFuncionariosPorTipo: List<Material> {
        return materialDao.listarMateriais()
    }
}