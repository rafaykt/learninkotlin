package com.example.learningrxjava.DataWeb

import com.google.gson.annotations.SerializedName

data class Film (
    val title : String,
                 @SerializedName("episode_id")
                 val episodeId : Int,
                 @SerializedName("characters")
                 val personUrls : List<String>
                 )