package com.example.runninappdh.ui.fragments


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runninappdh.R
import com.example.runninappdh.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RunFragment: Fragment(R.layout.fragment_run) {

    private val viewModel: MainViewModel by viewModels()
}