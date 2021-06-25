package com.example.firsttest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.firsttest.api.RetrofitAPI
import com.example.firsttest.model.ImageResponse
import com.example.firsttest.model.ImageResult
import com.example.firsttest.repository.ArtRepository
import com.example.firsttest.repository.ArtRepositoryInterface
import com.example.firsttest.roomdb.ArtDao
import com.example.firsttest.roomdb.ArtModel
import com.example.firsttest.util.Resource
import dagger.Provides
import kotlinx.coroutines.launch
import javax.inject.Singleton

class ArtViewModel @ViewModelInject constructor(
    private val repository : ArtRepositoryInterface
) : ViewModel() {

    val artList = repository.getArt()

    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
        get() = selectedImage

    private var insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    val insertArtMessage : LiveData<Resource<ArtModel>>
        get() = insertArtMsg

    //Solving the navigation bug
    fun resetInsertArtMsg() {
        insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    }

    fun setSelectedImage(url : String) {
        selectedImage.postValue(url)
    }

    fun deleteArt(art: ArtModel) = viewModelScope.launch {
        repository.deleteArt(art)
    }

    fun insertArt(art: ArtModel) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun makeArt(name : String, artistName : String, year : String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty() ) {
            insertArtMsg.postValue(Resource.error("Enter name, artist, year", null))
            return
        }
        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            insertArtMsg.postValue(Resource.error("Year should be number",null))
            return
        }

        val art = ArtModel(name, artistName, yearInt,selectedImage.value?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchForImage (searchString : String) {

        if(searchString.isEmpty()) {
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }


    }

}