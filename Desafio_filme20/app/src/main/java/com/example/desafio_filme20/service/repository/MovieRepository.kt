package com.example.desafio_filme20.service.repository

import android.content.Context
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.model.FilmResult
import com.example.desafio_filme20.service.model.MovieModel
import com.example.desafio_filme20.service.repository.remote.MovieService
import com.example.desafio_filme20.service.repository.remote.PriorityService
import com.example.desafio_filme20.service.repository.remote.RetrofitClient
import io.reactivex.Observable

class MovieRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(MovieService::class.java)

    fun loadMovies(): Observable<FilmResult> {
        return mRemote.getMoviePopularList()
//            .flatMap { filmResults -> Observable.fromIterable(filmResults.results) }
    }

}