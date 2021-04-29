package com.example.desafilme.repository.model

data class Movie(
    val id: Int,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int
)