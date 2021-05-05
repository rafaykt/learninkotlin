package com.example.desafio_filme20.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.repository.MovieRepository
import com.example.desafio_filme20.view.adapter.MovieAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = MovieRepository(application)
    private val mList = MutableLiveData<List<Film>>()
    var list: LiveData<List<Film>> = mList
    var listTeste = mutableListOf<Film>()

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    @SuppressLint("CheckResult")
    fun listPopularFilms() {
        mRepository.loadMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mList.postValue(it.results)
            }, { e ->
                e.printStackTrace()
            },
                {
                    movieAdapter?.notifyDataSetChanged()
                })

    }

    override fun onCleared() {
        super.onCleared()
    }

    fun addToFavorites(film: Film): Boolean {
        return mRepository.save(film)
    }


}