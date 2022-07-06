package com.example.musicapp.database


import com.example.musicapp.domain.DomainMusic
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface LocalDataRepository {
    fun getAllMusic(): Single<List<DomainMusic>>
    fun insertMusic(cards: List<DomainMusic>): Completable
    fun getArtistById(artistId: Int): Single<DomainMusic>
}

class LocalDataRepositoryImpl @Inject constructor(
    private val musicDAO: MusicDAO
) : LocalDataRepository {

    override fun getAllMusic(): Single<List<DomainMusic>> {
        return musicDAO.getAllMusic()
    }

    override fun insertMusic(cards: List<DomainMusic>): Completable {
        return musicDAO.insertAllMusic(*cards.toTypedArray())
    }

    override fun getArtistById(artistId : Int): Single<DomainMusic> {
        return musicDAO.getArtistById(artistId)
    }

}