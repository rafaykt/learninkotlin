package me.rafael.yokota.shared.viewmodel

import api_weather.WeatherSDK
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.rafael.yokota.shared.model.Coord
import me.rafael.yokota.shared.model.Main
import me.rafael.yokota.shared.model.Result
import me.rafael.yokota.shared.network.WeatherAPI

class ClimaViewModel() {

    private val _weatherNow = MutableStateFlow<Result>(
        Result(
            cod = 0,

            coord = Coord(0.0, 0.0),
            id = 0,
            main = Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0),
            name = "",
            weather = emptyList()
        )
    )
    val weatherNow: StateFlow<Result> get() = _weatherNow

    suspend fun getClimaTempo (lat: Double, long: Double)  {_weatherNow.value= WeatherAPI().getWeather(lat,long)}

    fun setCoords(lat: Double, lon: Double){

    }
}