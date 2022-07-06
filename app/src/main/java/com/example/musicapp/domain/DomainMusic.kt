package com.example.musicapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musicapp.model.Music

@Entity(tableName = "music_table")
data class DomainMusic(
    @PrimaryKey
    val id : Int,
    val artistName: String,
    val artworkUrl60: String,
    val collectionName: String,
    val trackPrice: Double,
)
    /**
     * This is an extension function to map the network data to the domain data
     *
     * Only a list type of MUSIC will be able to call this method
     */
    fun List<Music>.mapToDomainMusic(): List<DomainMusic> {
        return this.map { networkCard ->
            DomainMusic(
                id = networkCard.artistId ?: 9999,
                artistName = networkCard.artistName ?: "Invalid artist name",
                artworkUrl60 = networkCard.artworkUrl60 ?: "Invalid image",
                collectionName = networkCard.collectionName ?: "invalid collection name",
                trackPrice = networkCard.trackPrice ?: 0.0
            )
        }
    }
