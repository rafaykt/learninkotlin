package com.example.tasks.service.repository.remote

import com.example.tasks.service.constants.TaskConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var retrofit: Retrofit
        private var baseUrl = "http://devmasterteam.com/CursoAndroidAPI/"

        private var tokenKey = ""
        private var personKey = ""

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder()
                        .addHeader(TaskConstants.HEADER.PERSON_KEY, personKey)
                        .addHeader(TaskConstants.HEADER.TOKEN_KEY, tokenKey).build()
                    return chain.proceed(request)
                }
            })

            if (!Companion::retrofit.isInitialized)
                retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit
        }

        fun addHeader(token: String, personKey: String) {
            this.personKey = personKey
            this.tokenKey = token
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }

    }


}