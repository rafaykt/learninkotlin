package com.atilsamancioglu.artbookhilttesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atilsamancioglu.artbookhilttesting.MainCoroutineRule
import com.atilsamancioglu.artbookhilttesting.getOrAwaitValueTest
import com.atilsamancioglu.artbookhilttesting.repo.FakeRepository
import com.atilsamancioglu.artbookhilttesting.util.Status
import com.google.common.truth.Truth.assertThat
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class ArtViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()



    private lateinit var viewModel: ArtViewModel
    @Before
    fun setUp() {
        viewModel = ArtViewModel(FakeRepository())
    }

    @Test
    fun `insert art without year returns error`(){
        viewModel.makeArt("Mona lisa", "da vinci", "")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)


    }

    @Test
    fun `insert art without name returns error`(){
        viewModel.makeArt("", "da vinci", "1800")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artist name returns error`(){
        viewModel.makeArt("Mona lisa", "", "1800")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}