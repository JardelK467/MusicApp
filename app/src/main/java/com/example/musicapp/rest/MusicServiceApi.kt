package com.example.musicapp.rest

import com.example.musicapp.model.Music
import com.example.musicapp.model.MusicResponse
import retrofit2.http.GET
import io.reactivex.Single
import retrofit2.http.Query

interface MusicServiceApi {
        @GET(PATH)
        fun getSongs(@Query("term") musicType : String,
            @Query("amp;media") media: String = "music",
            @Query("amp;entity") song : String = "song",
            @Query("amp;limit") limit: Int = 50): Single<MusicResponse>


    companion object {
            const val BASE_URL = "https://itunes.apple.com/"
            private const val PATH = "search?term=pop&amp;media=music&amp;entity=song&amp;limit=50"
        }

}