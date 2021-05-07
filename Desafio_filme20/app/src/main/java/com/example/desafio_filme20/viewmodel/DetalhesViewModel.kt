package com.example.desafio_filme20.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.repository.MovieRepository

class DetalhesViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository = MovieRepository(application)
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun addToFavorites(film: Film) {
        film.favorite = true
        mRepository.save(film)
    }

    fun removeFromFavorites(film: Film) {
        film.favorite = false
        mRepository.delete(film)
    }

}