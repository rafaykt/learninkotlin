package com.example.desafio_filme20.service.repository.remote

import com.example.desafio_filme20.service.model.PriorityModel
import retrofit2.Call
import retrofit2.http.GET


interface PriorityService {

    @GET("Priority")
    fun list(): Call<List<PriorityModel>>

}