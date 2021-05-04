package com.example.desafilme.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Response(
        val page: Int,
        val results: List<Movie>,
        val total_pages: Int,
        val total_results: Int
):Parcelable