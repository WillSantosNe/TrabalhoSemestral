package com.example.trabalhosemestral.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trabalhosemestral.dao.FuncionarioDao
import com.example.trabalhosemestral.entity.Funcionario

@Database(entities = [Funcionario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun funcionarioDao(): FuncionarioDao

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
