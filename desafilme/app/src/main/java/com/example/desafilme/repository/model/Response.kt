package com.example.desafilme.repository.model

data class Response(
        val page: Int,
        val results: List<Movie>,
        val total_pages: Int,
        val total_results: Int
)