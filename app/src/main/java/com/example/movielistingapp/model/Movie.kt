package com.example.movielistingapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val desc: String,
    val genre: List<String>,
    val id: String,
    val posterurl: String,
    val rating: String,
    val release: String,
    val title: String
):Parcelable