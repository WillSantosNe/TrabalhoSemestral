package com.example.trabalhosemestral.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Classe mãe
@Entity(tableName = "funcionarios")
open class Funcionario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val usuario: String,
    val senha: String,
    val tipo: String // Campo para diferenciar subclasses
)


// Subclasse Gerente
@Entity(tableName = "gerentes")
data class Gerente(
    val funcionarioId: Int // Relacionamento com Funcionario
) : Funcionario(usuario = "userGerente", senha = "12345", tipo = "Gerente")


// Subclasse Operador
@Entity(tableName = "operadores")
data class Operador(
    val funcionarioId: Int // Relacionamento com Funcionario
) : Funcionario(usuario = "userOperador", senha = "12345", tipo = "Operador")


// Subclasse Administrador
@Entity(tableName = "administradores")
data class Administrador(
    val funcionarioId: Int // Relacionamento com Funcionario
) : Funcionario(usuario = "userAdministrador", senha = "12345", tipo = "Administrador")