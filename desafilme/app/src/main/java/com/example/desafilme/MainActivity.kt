package com.example.desafilme

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.desafilme.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    var listView: ListView? = null
    var movies= mutableListOf<String>()
    var movieAdapter: ArrayAdapter<String>? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        listView = ListView(this)
        setContentView(listView)
        movieAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, movies)
        listView?.adapter = movieAdapter

        val api = MovieRepository()
        api.loadMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ movie ->
                    movies?.add(movie.original_title)
                }, { e ->
                    e.printStackTrace()
                },{
                    movieAdapter?.notifyDataSetChanged()
                })


    }
}