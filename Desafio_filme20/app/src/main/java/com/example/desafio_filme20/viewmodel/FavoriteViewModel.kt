package com.example.desafio_filme20.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.repository.MovieRepository
import com.example.desafio_filme20.view.HomeFragmentDirections
import com.example.desafio_filme20.view.adapter.FavoriteAdapter
import com.example.desafio_filme20.view.adapter.MovieAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
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