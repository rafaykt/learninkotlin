package com.example.desafio_filme20.service.repository

import android.content.Context
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.model.FilmResult
import com.example.desafio_filme20.service.repository.local.FilmDataBase
import com.example.desafio_filme20.service.repository.remote.MovieService
import com.example.desafio_filme20.service.repository.remote.RetrofitClient
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MovieRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(MovieService::class.java)
    private val mDataBaseLocal = FilmDataBase.getDatabase(context).filmDAO()

    fun loadMovies(): Observable<FilmResult> {
        return mRemote.getMoviePopularList()
    }

    fun save(film: Film): Completable {
        return Completable.create {
            try {
                mDataBaseLocal.save(film)
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }


    fun delete(film: Film): Completable {
        return Completable.create {
            try{
                mDataBaseLocal.delete(film)
                it.onComplete()
            } catch(e: Exception){
                it.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    fun loadFavoriteMovies(): Observable<List<Film>> {
        return mDataBaseLocal.getFavoriteList()
    }

    fun isFavorite(film: Film): Boolean {
        return mDataBaseLocal.isFavorite(film.id)
    }

}