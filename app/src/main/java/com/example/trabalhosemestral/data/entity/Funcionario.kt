package com.example.trabalhosemestral.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "funcionarios")
data class Funcionario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val usuario: String,
    val senha: String,
    val tipo: String // Administrador, Gerente, Operador
)
