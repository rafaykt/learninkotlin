package com.improving.testing_20.data

import com.improving.testing_20.BuildConfig
import com.improving.testing_20.data.responses.ImageResponse
import com.improving.testing_20.data.responses.ImageResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {
    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY

    ): Response<ImageResponse>



}