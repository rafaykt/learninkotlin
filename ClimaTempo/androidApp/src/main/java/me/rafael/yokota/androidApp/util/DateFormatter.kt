package me.rafael.yokota.androidApp.util

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    fun formatDate(unixDate: String): String? {
        try {
            val simpleDateFormat = SimpleDateFormat("dd/MM")
            val formattedDate = Date(unixDate.toLong() * 1000)
            return simpleDateFormat.format(formattedDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    fun getMinMaxFormatString(min: String, max :String): String {
        return "${String.format("%.1f",min.toDouble()-273.17)}ºC / ${String.format("%.1f",max.toDouble()-273.17)}ºC"

    }

}