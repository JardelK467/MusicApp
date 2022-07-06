package com.example.musicapp.rest

import com.example.musicapp.model.Music
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.rest.MusicServiceApi
import io.reactivex.Single
import javax.inject.Inject

interface MusicRepository {
    fun getPop(): Single<MusicResponse>
    fun getRock(): Single<MusicResponse>
    fun getClassic(): Single<MusicResponse>
}

class MusicRepositoryImpl @Inject constructor(
    private val musicServiceApi: MusicServiceApi
) : MusicRepository {

    override fun getPop(): Single<MusicResponse> {
        return musicServiceApi.getSongs("pop")
    }
    override fun getRock(): Single<MusicResponse> {
        return musicServiceApi.getSongs("rock")
    }
    override fun getClassic(): Single<MusicResponse> {
        return musicServiceApi.getSongs("classick")
    }


}