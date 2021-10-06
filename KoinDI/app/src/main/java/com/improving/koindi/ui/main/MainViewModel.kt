package com.improving.koindi.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.*

class MainViewModel(
        private val repository: MainRepository,
        private val navController: NavController
    ) : ViewModel() {

    val filmesLiveData = MutableLiveData<List<Filme>>()

    fun getFilmes() {
        viewModelScope.launch(Dispatchers.IO) {
            val filmes = withContext(Dispatchers.Default){
                repository.getFilmes()
            }
            filmesLiveData.postValue(filmes)
        }
    }

}