package com.improving.koindi.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val filmesLiveData = MutableLiveData<List<Filme>>()

    fun getFilmes() {
        viewModelScope.launch(Dispatchers.IO) {
            val filmes = withContext(Dispatchers.Default){
                repository.getFilmes()
            }
            filmesLiveData.postValue(filmes)
        }
    }

    class MainViewModelFactory(
        private val repository: MainRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}