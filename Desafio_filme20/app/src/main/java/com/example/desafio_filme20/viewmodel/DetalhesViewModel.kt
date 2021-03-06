package com.example.desafio_filme20.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetalhesViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository = MovieRepository(application)
    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
    var compositeDisposable = CompositeDisposable()
    fun addToFavorites(film: Film) {
        film.favorite=true
        compositeDisposable.add(mRepository.save(film)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe())
    }

    fun removeFromFavorites(film: Film) {
        film.favorite = false
        compositeDisposable.remove(mRepository.delete(film).subscribe{})
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}