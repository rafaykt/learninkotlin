package com.example.desafilme.repository.remote

import com.example.desafilme.repository.DataWeb.FilmResult
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieService {
    @GET("popular?api_key=7768c7c1831141e5456595624be2d1c7")
    fun getMoviePopularList(): Observable<FilmResult>
}