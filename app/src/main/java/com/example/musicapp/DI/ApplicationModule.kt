package com.example.musicapp.DI

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.musicapp.database.MusicDAO
import com.example.musicapp.database.MusicDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context = application.applicationContext

    @Provides
    fun provideRoomDb(context: Context): MusicDatabase =
        Room.databaseBuilder(
            context,
            MusicDatabase::class.java,
            "music-db"
        )
            .build()

    @Provides
    fun providesMusicDAO(database: MusicDatabase): MusicDAO =
        database.getMusicDAO()
}