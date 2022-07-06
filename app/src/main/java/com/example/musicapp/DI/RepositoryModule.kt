package com.example.musicapp.DI

import com.example.musicapp.database.LocalDataRepository
import com.example.musicapp.database.LocalDataRepositoryImpl
import com.example.musicapp.rest.MusicRepository
import com.example.musicapp.rest.MusicRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideLocalRepository(
        localDataRepositoryImpl: LocalDataRepositoryImpl
    ): LocalDataRepository

    @Binds
    abstract fun provideNetworkRepository(
        networkRepositoryImpl: MusicRepositoryImpl
    ): MusicRepository

}