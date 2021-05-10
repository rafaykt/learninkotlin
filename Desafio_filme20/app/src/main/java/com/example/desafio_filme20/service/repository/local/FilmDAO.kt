package com.example.desafio_filme20.service.repository.local

import androidx.room.*
import com.example.desafio_filme20.service.model.Film
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface FilmDAO {

    @Insert
    fun save( film: Film)


    @Delete
    fun delete(film: Film)

    @Query("SELECT * FROM Film ORDER BY popularity DESC")
    fun getFavoriteList () : Observable<List<Film>>

    @Query("SELECT favorite FROM Film WHERE id = :id")
    fun isFavorite(id: Int): Boolean
}