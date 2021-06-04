package me.rafael.yokota.shared.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.rafael.yokota.shared.Repository
import me.rafael.yokota.shared.model.*
import me.rafael.yokota.shared.network.WeatherAPI

class ClimaViewModel() {
    private val mRepository = Repository()
    private val _weatherNow = MutableStateFlow<Result>(
        Result(
            cod = 0,
            coord = Coord(0.0, 0.0),
            id = 0,
            main = Main(0.0, 0.0, 0.0, 0.0),
            name = "",
            weather = emptyList()
        )
    )
    val weatherNow: StateFlow<Result> get() = _weatherNow


    private val _oneCallWeather = MutableStateFlow<OneCallResult>(
        OneCallResult(
            lat = 0.0,
            lon = 0.0,
            timezone_offset = 0,
            daily = emptyList()
        )
    )

    val oneCallWeather: StateFlow<OneCallResult> get() = _oneCallWeather



    suspend fun getClimaTempo (lat: Double, long: Double)  {
        _weatherNow.value= mRepository.getWeather(lat,long)
        getOneCallData(lat,long)

    }

    suspend fun getOneCallData(lat: Double, long: Double) {
        _oneCallWeather.value = mRepository.getOneCallApi(lat, long)
    }
}