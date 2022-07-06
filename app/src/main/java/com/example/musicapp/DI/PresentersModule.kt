package com.example.musicapp.DI

import com.example.musicapp.presenter.*
import dagger.Binds
import dagger.Module

@Module
abstract class PresentersModule {

    @Binds
    abstract fun providesRockPresenter(
        rockPresenter: RockPresenter
    ): RockPresenterContract

    @Binds
    abstract fun providesClassicPresenter(
        classicPresenter: ClassicPresenter
    ): ClassicPresenterContract

    @Binds
    abstract fun providesPopPresenter(
        popPresenter: PopPresenter
    ): PopPresenterContract
}