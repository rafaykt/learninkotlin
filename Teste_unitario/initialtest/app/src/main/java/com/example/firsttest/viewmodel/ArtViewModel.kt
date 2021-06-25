package com.example.firsttest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firsttest.model.ImageResponse
import com.example.firsttest.model.ImageResult
import com.example.firsttest.repository.ArtRepositoryInterface
import com.example.firsttest.roomdb.ArtModel
import com.example.firsttest.util.Resource
import kotlinx.coroutines.launch


class ArtViewModel @ViewModelInject constructor(
    private val repository: ArtRepositoryInterface
) : ViewModel() {

    //Art fragment

    val artList = repository.getArt()


    // Image api fragment

    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList: LiveData<Resource<ImageResponse>> get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String> get() = selectedImage

    //ArtDetailsFragment

    private var insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    val insertArtMessage: LiveData<Resource<ArtModel>> get() = insertArtMsg

    fun resetInsertArtMsg() {
        insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    }

    fun setSelectedImage(url: String) {
        selectedImage.postValue(url)
    }

    fun deleteArt(art: ArtModel) = viewModelScope.launch {
        repository.deleteArt(art)
    }

    fun insertArt(art: ArtModel) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun makeArt(name: String, artistName: String, year: String){
        if(name.isEmpty() || artistName.isEmpty()|| year.isEmpty()) {
            insertArtMsg.postValue(Resource.error("Enter name, artist, year", null))
        }

        val yearInt = try{
            year.toInt()
        }catch( exception: Exception){
            insertArtMsg.postValue(Resource.error(("year should be a number", null))
            return
        }

        val art = ArtModel(name, artistName, yearInt, selectedImage.value ?: "")

        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchForImage(imageString: String) {
        if (imageString.isEmpty()) {
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(imageString)
            images.value = response
        }
    }
}