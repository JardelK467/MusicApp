package com.example.musicapp.model

import com.google.gson.annotations.SerializedName

data class Music(
    @SerializedName("artistId")
    val artistId: Int,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("trackPrice")
    val trackPrice: Double,
)