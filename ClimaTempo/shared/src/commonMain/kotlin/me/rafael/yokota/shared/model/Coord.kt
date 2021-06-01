package me.rafael.yokota.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    var lat: Double,
    var lon: Double
)