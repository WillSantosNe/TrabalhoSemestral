package com.example.trabalhosemestral.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materiais")
open class Material(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val marca: String,
    val quantidade: Int,
    
)