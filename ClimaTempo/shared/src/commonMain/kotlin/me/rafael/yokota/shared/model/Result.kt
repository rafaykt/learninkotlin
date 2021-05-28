package me.rafael.yokota.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val cod: Int,
    val coord: Coord,
    val id: Int,
    val main: Main,
    val name: String,
    val weather: List<Weather>
)