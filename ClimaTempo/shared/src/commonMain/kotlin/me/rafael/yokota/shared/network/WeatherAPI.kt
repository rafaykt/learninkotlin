package me.rafael.yokota.shared.network


import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import me.rafael.yokota.shared.constants.Constants
import me.rafael.yokota.shared.model.OneCallResult
import me.rafael.yokota.shared.model.Result

class WeatherAPI {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }



    suspend fun getWeather(lat: Double, lon: Double): Result {
        return httpClient.get(Constants.API.baseUrl+"&lat=${lat}&lon=${lon}")

    }

    suspend fun getOneCallApi(lat: Double, lon: Double): OneCallResult {
        println(Constants.API.baseOneCallApiUrl+"&lat=${lat}&lon=${lon}")
        return httpClient.get(Constants.API.baseOneCallApiUrl+"&lat=${lat}&lon=${lon}")

    }
}