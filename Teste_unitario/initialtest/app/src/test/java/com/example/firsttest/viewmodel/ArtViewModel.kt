package com.example.firsttest.viewmodel

import org.junit.Before

class ArtViewModelTest {

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){
        //Test Doubles


        viewModel = ArtViewModel(ArtRepository)
    }
}