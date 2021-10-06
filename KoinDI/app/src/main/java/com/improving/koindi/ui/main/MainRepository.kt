package com.improving.koindi.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepository {
    suspend fun getFilmes(): List<Filme>{
        return withContext(Dispatchers.Default){
            delay (3000)
            listOf(Filme(1, "Titulo 01"), Filme(2, "Titulo 02"))
        }
    }
}