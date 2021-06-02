package me.rafael.yokota.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Main(
    val feels_like: Double,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)