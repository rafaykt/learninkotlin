package com.example.firsttest.repository

import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.cache.MemoryCache
import com.example.firsttest.api.RetrofitAPI
import com.example.firsttest.model.ImageResponse
import com.example.firsttest.roomdb.ArtDao
import com.example.firsttest.roomdb.ArtModel
import com.example.firsttest.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetrofitAPI
) : ArtRepositoryInterface {
    override suspend fun insertArt(art: ArtModel) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: ArtModel) {
        deleteArt(art)
    }

    override fun getArt(): LiveData<List<ArtModel>>{
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try{
            val response = retrofitAPI.imageSearch(imageString)
            if(response.isSuccessful){
                response.body()?.let{
                    return@let Resource.success(it)
                }?: Resource.error("Error", null)
            }else{
                Resource.error("Error", null)
            }
        }catch(e: Exception){
            Resource.error("No data", null)
        }

    }


}