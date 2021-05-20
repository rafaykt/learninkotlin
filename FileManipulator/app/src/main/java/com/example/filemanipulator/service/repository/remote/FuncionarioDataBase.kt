package com.example.filemanipulator.service.repository.remote

import android.content.Context
import androidx.room.*
import com.example.filemanipulator.service.model.Funcionario

@Database(entities = [Funcionario::class], version=2, exportSchema = false)
abstract class FuncionarioDataBase: RoomDatabase() {

    abstract fun funcionarioDAO() : FuncionarioDAO
    companion object {
        private lateinit var INSTANCE: FuncionarioDataBase
        fun getDatabase(context: Context) : FuncionarioDataBase{
            if(!::INSTANCE.isInitialized){
                INSTANCE = Room.databaseBuilder(
                    context,
                    FuncionarioDataBase::class.java,
                    "funcionarioDB"
                )
                    .build()

            }
            return INSTANCE
        }

    }
}