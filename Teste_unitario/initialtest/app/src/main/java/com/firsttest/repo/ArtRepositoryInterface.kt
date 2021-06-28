package com.firsttest.repo

import androidx.lifecycle.LiveData
import com.firsttest.model.ImageResponse
import com.firsttest.model.ImageResult
import com.firsttest.roomdb.Art
import com.firsttest.util.Resource
import retrofit2.Response

interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>

}