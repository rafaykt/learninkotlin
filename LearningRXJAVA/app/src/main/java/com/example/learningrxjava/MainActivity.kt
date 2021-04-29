package com.example.learningrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.learningrxjava.API.StarWarsApi
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    var listView: ListView? = null
    var movies= mutableListOf<String>()
    var movieAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = ListView(this)
        setContentView(listView)
        movieAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, movies)
        listView?.adapter = movieAdapter

        val api = StarWarsApi()
        api.loadMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ movie ->
                movies?.add("${movie.title} -- ${movie.episodeId}")
            }, { e ->
                e.printStackTrace()
            },{
                movieAdapter?.notifyDataSetChanged()
            })
    }
}