package com.improving.koindi.di

import androidx.navigation.NavController
import com.improving.koindi.ui.main.MainRepository
import com.improving.koindi.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val mainModule = module {
    factory{
        MainRepository()
    }

    viewModel{ (navController: NavController)->
        MainViewModel(
            repository = get(),
            navController= navController
        )
    }
}