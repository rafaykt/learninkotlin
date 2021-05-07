package com.example.desafio_filme20.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.repository.MovieRepository
import com.example.desafio_filme20.view.adapter.MovieAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = MovieRepository(application)
    private val mList = MutableLiveData<List<Film>>()
    var list: LiveData<List<Film>> = mList
    var listTeste = mutableListOf<Film>()



    @SuppressLint("CheckResult")
    fun listPopularFilms() {
        CompositeDisposable(
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



    fun addToFavorites(film: Film): Boolean {
        return mRepository.save(film)
    }

    fun removeFromFavorites(film: Film) {
        film.favorite = false
        mRepository.delete(film)
    }

    private fun verificaFavorito(filmList: List<Film>): List<Film> {
        filmList.forEach { it.favorite = mRepository.isFavorite(it) }
        return filmList
    }

    override fun onCleared() {
        super.onCleared()
        CompositeDisposable().dispose()
    }

}