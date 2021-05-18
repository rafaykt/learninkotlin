package com.example.desafio_filme20.service.repository.remote

import com.example.desafio_filme20.service.constants.MovieConstants
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.model.FilmResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("popular?api_key=${MovieConstants.API.key}")
    fun getMoviePopularList(): Observable<FilmResult>
}