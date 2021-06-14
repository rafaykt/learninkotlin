package com.example.runninappdh.ui.fragments


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runninappdh.R
import com.example.runninappdh.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment: Fragment(R.layout.fragment_tracking) {

    private val viewModel: MainViewModel by viewModels()

}