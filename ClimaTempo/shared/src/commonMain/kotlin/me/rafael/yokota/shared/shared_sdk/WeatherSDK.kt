package me.rafael.yokota.shared.shared_sdk

import me.rafael.yokota.shared.model.OneCallResult
import me.rafael.yokota.shared.model.Result
import me.rafael.yokota.shared.network.WeatherAPI

class WeatherSDK() {
    private val api = WeatherAPI()

    @Throws(Exception::class)
    suspend fun getWeatherDetails(lat: Double, long: Double): Result {
        return api.getWeather(lat,long)
    }

    @Throws(Exception::class)
    suspend fun getWeatherOneCall(lat: Double, long: Double): OneCallResult {

        return api.getOneCallApi(lat,long)

    }

}
