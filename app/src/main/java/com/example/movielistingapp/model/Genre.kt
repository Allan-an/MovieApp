package com.example.movielistingapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val genreTitle: String,
    val id: String,
    val moviesList: List<Movie>,
    val type: Int
):Parcelable