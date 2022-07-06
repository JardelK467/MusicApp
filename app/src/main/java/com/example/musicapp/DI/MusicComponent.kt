package com.example.musicapp.DI

import com.example.musicapp.MainActivity
import com.example.musicapp.view.PopFragment
import com.example.musicapp.view.ClassicFragment
import com.example.musicapp.view.RockFragment
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        RestModule::class,
        RepositoryModule::class,
        PresentersModule::class
    ]
)
interface MusicComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(popFragment: PopFragment)
    fun inject(rockFragment: RockFragment)
    fun inject(classicFragment: ClassicFragment)
}