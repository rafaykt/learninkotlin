package com.example.desafilme.repository

import android.content.Context
import com.example.desafilme.repository.DataWeb.Film
import com.example.desafilme.repository.DataWeb.FilmResult
import com.example.desafilme.repository.model.Movie as Movie
import com.example.desafilme.repository.remote.MovieService
import com.example.desafilme.repository.remote.RetrofitClient
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {
    val service: MovieService

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client((httpClient.build()))
                .build()
        service = retrofit.create<MovieService>(MovieService::class.java)
    }

    fun loadMovies(): Observable<Movie> {
        return service.getMoviePopularList()
                .flatMap { filmResults -> Observable.fromIterable(filmResults.results) }
                .map{ film -> Movie(film.id, film.original_title, film.overview, film.popularity, film.poster_path, film.release_date, film.vote_average, film.vote_count)}
    }
}


