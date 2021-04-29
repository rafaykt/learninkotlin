package com.example.learningrxjava.API

import android.net.Uri
import com.example.learningrxjava.Model.Character
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import com.example.learningrxjava.Model.Movie as Movie

class StarWarsApi {
    val service: SWInterface

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(70L, TimeUnit.SECONDS)
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create<SWInterface>(SWInterface::class.java)

    }

    fun loadMovies(): Observable<Movie> {
        return service.listMovies()
            .flatMap { filmResults -> Observable.fromIterable(filmResults.results)}
            .map { film ->
                Movie(film.title, film.episodeId)
            }
    }

}