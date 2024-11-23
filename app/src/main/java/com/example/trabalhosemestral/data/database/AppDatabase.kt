package com.example.trabalhosemestral.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trabalhosemestral.data.dao.FuncionarioDao
import com.example.trabalhosemestral.data.dao.MaterialDao
import com.example.trabalhosemestral.data.entity.Funcionario
import com.example.trabalhosemestral.data.entity.Material

@Database(entities = [Funcionario::class, Material::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun funcionarioDao(): FuncionarioDao
    abstract fun materialDao(): MaterialDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Garante que apenas uma instância do banco de dados seja criada
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
