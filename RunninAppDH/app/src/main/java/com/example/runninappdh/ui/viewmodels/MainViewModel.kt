package com.example.runninappdh.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.runninappdh.repositories.MainRepository
import javax.inject.Inject


class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {
}