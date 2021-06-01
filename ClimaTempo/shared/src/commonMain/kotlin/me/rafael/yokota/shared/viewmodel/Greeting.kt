package me.rafael.yokota.shared.viewmodel

import api_weather.WeatherSDK


class Greeting {
    suspend fun greeting(lat: Double, lon: Double): String {
        return WeatherSDK().getWeatherDetails(lat,lon).toString()
    }
}
