package me.rafael.yokota.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class OneCallResult(
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone_offset: Int,
)