package com.example.desafio_filme20.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.repository.MovieRepository
import com.example.desafio_filme20.view.adapter.MovieAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = MovieRepository(application)
    private val mList = MutableLiveData<List<Film>>()
    var list: LiveData<List<Film>> = mList
    var listTeste = mutableListOf<Film>()

    var compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun listPopularFilms() {
        compositeDisposable.add(
            mRepository.loadMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    mList.postValue(verificaFavorito(it.results))
                }, { e ->
                    e.printStackTrace()
                })
        )
    }

    fun addToFavorites(film: Film){
        film.favorite = true
        compositeDisposable.add(mRepository.save(film).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe{})
    }

    fun removeFromFavorites(film: Film) {
        film.favorite = false
        compositeDisposable.add(mRepository.delete(film).subscribe{})
    }

    private fun verificaFavorito(filmList: List<Film>): List<Film> {
        filmList.forEach { it.favorite = mRepository.isFavorite(it) }
        return filmList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}