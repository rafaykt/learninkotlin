package com.example.desafilme.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafilme.repository.MovieRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mMovieRepository = MovieRepository()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

}