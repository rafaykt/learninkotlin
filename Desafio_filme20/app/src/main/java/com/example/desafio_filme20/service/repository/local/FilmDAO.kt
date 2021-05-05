package com.example.desafio_filme20.service.repository.local

import androidx.room.*
import com.example.desafio_filme20.service.model.Film

@Dao
interface FilmDAO {

    @Insert
    fun save( film: Film): Long

    @Update
    fun update( film: Film): Int

    @Delete
    fun delete(film: Film)

    @Query("SELECT * FROM Film where favorite = 1")
    fun getFavoriteList () : List< Film >

}