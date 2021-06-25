package com.example.firsttest.repository

import androidx.lifecycle.LiveData
import com.example.firsttest.model.ImageResponse
import com.example.firsttest.roomdb.ArtModel
import com.example.firsttest.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art: ArtModel)

    suspend fun deleteArt(art: ArtModel)

    fun getArt(): LiveData<List<ArtModel>>

    suspend fun searchImage(imageString: String): Resource<ImageResponse>


}