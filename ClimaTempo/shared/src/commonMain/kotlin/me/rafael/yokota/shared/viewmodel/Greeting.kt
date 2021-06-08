package me.rafael.yokota.shared.viewmodel

import me.rafael.yokota.shared.shared_sdk.WeatherSDK


class Greeting {
    suspend fun greeting(lat: Double, lon: Double): String {
        return WeatherSDK().getWeatherDetails(lat,lon).toString()
    }
}
