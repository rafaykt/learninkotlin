package me.rafael.yokota.shared.network


import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import me.rafael.yokota.shared.model.Result

class WeatherAPI {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    companion object {
        private const val LAUNCHES_ENDPOINT = "" +
                "https://api.openweathermap.org/data/2.5/weather?" +
                "&appid=880664442efed4788979022ef7a60484" +
                "&lang=pt_br"
    }

    suspend fun getWeather(lat: Double, lon: Double): Result {
        return httpClient.get(LAUNCHES_ENDPOINT+"&lat=${lat}&lon=${lon}")

    }
}