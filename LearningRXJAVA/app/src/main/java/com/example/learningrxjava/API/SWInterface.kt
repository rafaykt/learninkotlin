package com.example.learningrxjava.API

import com.example.learningrxjava.DataWeb.FilmResult
import com.example.learningrxjava.DataWeb.Person
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface SWInterface {
        @GET("films")
        fun listMovies() : Observable<FilmResult>
}