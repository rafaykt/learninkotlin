package com.example.desafio_filme20.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.desafio_filme20.service.model.Film

@Database(entities = [Film::class], version =1, exportSchema = false)
abstract class FilmDataBase: RoomDatabase() {

    abstract fun filmDAO(): FilmDAO

    companion object{
        private lateinit var INSTANCE: FilmDataBase
        fun getDatabase(context: Context) : FilmDataBase{
            if (!::INSTANCE.isInitialized){
                 INSTANCE = Room.databaseBuilder(context, FilmDataBase::class.java, "filmDB")
//                     .allowMainThreadQueries()
                     .build()
            }
            return INSTANCE
        }
    }

}