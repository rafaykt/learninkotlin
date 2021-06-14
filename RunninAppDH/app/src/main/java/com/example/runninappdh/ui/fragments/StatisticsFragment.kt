package com.example.runninappdh.ui.fragments


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runninappdh.R
import com.example.runninappdh.ui.viewmodels.MainViewModel
import com.example.runninappdh.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment: Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()
}