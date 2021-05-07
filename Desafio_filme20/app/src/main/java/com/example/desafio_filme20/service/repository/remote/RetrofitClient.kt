package com.example.desafio_filme20.service.repository.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var retrofit: Retrofit
        private var baseUrl = "https://api.themoviedb.org/3/movie/"


        private fun getRetrofitInstance(): Retrofit {
            val logging by lazy {
                val log = HttpLoggingInterceptor()
                log.level = HttpLoggingInterceptor.Level.BODY
                return@lazy log
            }
            val httpClient = OkHttpClient.Builder().addInterceptor(logging)


            if (!Companion::retrofit.isInitialized)
                retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit
        }


        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }

    }


}