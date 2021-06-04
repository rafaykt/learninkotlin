package me.rafael.yokota.shared

import me.rafael.yokota.shared.model.OneCallResult
import me.rafael.yokota.shared.model.Result
import me.rafael.yokota.shared.shared_sdk.WeatherSDK

class Repository {

    private var sdkWeather = WeatherSDK()
    suspend fun getWeather(lat: Double, lon: Double): Result {
        return sdkWeather.getWeatherDetails(lat,lon)
    }

    suspend fun getOneCallApi(lat: Double, lon: Double): OneCallResult{
        println("got one call api")
        return sdkWeather.getWeatherOneCall(lat,lon)
    }
}