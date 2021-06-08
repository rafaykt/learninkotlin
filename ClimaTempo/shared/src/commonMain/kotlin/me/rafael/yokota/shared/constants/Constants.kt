package me.rafael.yokota.shared.constants

class Constants {
    object API{
        const val baseUrl = "https://api.openweathermap.org/data/2.5/weather?" +
                "&appid=880664442efed4788979022ef7a60484" +
                "&lang=pt_br"
        const val basUrlIcon = "http://openweathermap.org/img/wn/"

        const val baseOneCallApiUrl = "https://api.openweathermap.org/data/2.5/onecall?" +
                "appid=880664442efed4788979022ef7a60484" +
                "&exclude=hourly,minutely,current,alerts&lang=pt_br"
    }

}