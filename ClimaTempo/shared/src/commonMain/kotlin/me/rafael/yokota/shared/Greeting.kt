package me.rafael.yokota.shared

import api_weather.WeatherSDK


class Greeting {
    suspend fun greeting(lat: Float, lon: Float): String {
        return WeatherSDK().getWeatherDetails(lat,lon).toString()
    }
}
