package me.rafael.yokota.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Daily(
    val dt: Int,
    val feels_like: FeelsLike,
    val temp: Temp,
    val weather: List<Weather>,
)