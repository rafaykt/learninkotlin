package com.example.tasks.service.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var retrofit: Retrofit
        private var baseUrl = "http://devmasterteam.com/CursoAndroidAPI/"


        private fun getRetrofitInstance () : Retrofit{
            val httpClient = OkHttpClient.Builder()
            if(!Companion::retrofit.isInitialized)
                retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit
        }

        fun<S> createService(serviceClass: Class<S>): S{
            return getRetrofitInstance().create(serviceClass)
        }

    }



}