package com.example.desafio_filme20.service.repository

import android.content.Context
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.model.FilmResult
import com.example.desafio_filme20.service.model.MovieModel
import com.example.desafio_filme20.service.repository.local.FilmDataBase
import com.example.desafio_filme20.service.repository.remote.MovieService
import com.example.desafio_filme20.service.repository.remote.PriorityService
import com.example.desafio_filme20.service.repository.remote.RetrofitClient
import io.reactivex.Observable

class MovieRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(MovieService::class.java)
    private val mDataBaseLocal = FilmDataBase.getDatabase(context).filmDAO()

    fun loadMovies(): Observable<FilmResult> {
        return mRemote.getMoviePopularList()
//            .flatMap { filmResults -> Observable.fromIterable(filmResults.results) }
    }


    fun save(film: Film): Boolean{
        film.favorite=true
        return mDataBaseLocal.save(film) > 0
    }

    fun getFavoritesList() : List<Film> {
        return mDataBaseLocal.getFavoriteList()
    }

    fun update(film: Film): Boolean {
        return mDataBaseLocal.update(film) > 0
    }

    fun delete(film: Film){
        return mDataBaseLocal.delete(film)
    }
}