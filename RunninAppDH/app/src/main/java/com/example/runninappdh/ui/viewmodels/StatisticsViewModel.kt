package com.example.runninappdh.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.runninappdh.repositories.MainRepository
import javax.inject.Inject


class StatisticsViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {
}