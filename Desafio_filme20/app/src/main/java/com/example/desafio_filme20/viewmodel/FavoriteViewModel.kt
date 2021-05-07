package com.example.desafio_filme20.viewmodel

import android.app.Application

import androidx.lifecycle.*

import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.repository.MovieRepository

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = MovieRepository(application)
    private val mList = MutableLiveData<List<Film>>()
    var list: LiveData<List<Film>> = mList

    fun getListFavoriteFilms() {
        CompositeDisposable(
            mRepository.loadFavoriteMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    mList.postValue(it)
                }, { e ->
                    e.printStackTrace()
                })
        )
    }

    fun removeFromFavorites(film: Film) {
        film.favorite = false
        mRepository.delete(film)
    }

    override fun onCleared() {
        super.onCleared()
        CompositeDisposable().dispose()
    }

}