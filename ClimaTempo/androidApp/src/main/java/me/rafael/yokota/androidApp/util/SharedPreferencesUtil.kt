package me.rafael.yokota.androidApp.util

import android.content.Context

import me.rafael.yokota.shared.model.Coord


class SharedPreferencesUtil{

    companion object {

        //Pref name
        private val APP_PREFERENCES = "dados"

        fun getCoordenadas(context: Context ): Coord {
            val sharedPreferences = context.getSharedPreferences(
                APP_PREFERENCES,
                Context.MODE_PRIVATE
            )
            val coord = Coord(
                sharedPreferences.getFloat("latitude", 0F).toDouble(),
                sharedPreferences.getFloat("longitude", 0F).toDouble()
            )
            return coord

        }
        fun setCoordenadas(context: Context, lat: Double, lon: Double){
            val sharedPreferences = context.getSharedPreferences(
                APP_PREFERENCES,
                Context.MODE_PRIVATE
            )

            val coords = sharedPreferences.edit()
            coords.putFloat("latitude",lat.toFloat())
            coords.putFloat("longitude", lon.toFloat())
            coords.apply()
        }
    }

}
