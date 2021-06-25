package com.example.firsttest.model

import com.google.gson.annotations.SerializedName

data class ImageResult(
    val comments: Int,
    val downloads: Int,
            val favorites: Int,
            val id: Int,
            val imageSize: Int,
            val imageHeight: Int,
            val imageWidth: Int,
            val likes: Int,
            val views: Int,
            val webformatHeight: Int,
            val pageURL:String,
            val previewHeight:String,
            val tags:String,
            val type:String,
            val user:String,
            val previewURL:String,
            val userImageURL:String,
            val largeImageURL:String,
            @SerializedName("user_id")
            val userId: Int,
)