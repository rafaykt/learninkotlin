package api_weather

import me.rafael.yokota.shared.model.Result
import me.rafael.yokota.shared.network.WeatherAPI

class WeatherSDK() {
    private val api = WeatherAPI()

    @Throws(Exception::class)
    suspend fun getWeatherDetails(lat: Double, long: Double): Result {
        return api.getWeather(lat,long)
    }
}
