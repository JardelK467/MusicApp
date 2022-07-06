package com.example.musicapp.model

import com.google.gson.annotations.SerializedName



data class MusicResponse(
    @SerializedName("resultCount")
    val resultsCount: Int?,
    @SerializedName("results")
    val musicResults: List<Music>
)