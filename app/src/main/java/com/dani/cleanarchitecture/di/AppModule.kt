package com.dani.cleanarchitecture.di

import android.app.Application
import androidx.room.Room
import com.dani.cleanarchitecture.data.local.AppDatabase
import com.dani.cleanarchitecture.data.local.Converters
import com.dani.cleanarchitecture.data.local.dao.AlbumDao
import com.dani.cleanarchitecture.data.local.dao.ArtistDao
import com.dani.cleanarchitecture.data.local.dao.SongDao
import com.dani.cleanarchitecture.data.local.dao.WordInfoDao
import com.dani.cleanarchitecture.data.local.utils.GsonParser
import com.dani.cleanarchitecture.data.remote.DictionaryApi
import com.dani.cleanarchitecture.data.repository.AlbumRepositoryImpl
import com.dani.cleanarchitecture.data.repository.ArtistRepositoryImpl
import com.dani.cleanarchitecture.data.repository.SongRepositoryImpl
import com.dani.cleanarchitecture.data.repository.WordInfoRepositoryImpl
import com.dani.cleanarchitecture.domain.repository.AlbumRepository
import com.dani.cleanarchitecture.domain.repository.ArtistRepository
import com.dani.cleanarchitecture.domain.repository.SongRepository
import com.dani.cleanarchitecture.domain.repository.WordInfoRepository
import com.dani.cleanarchitecture.domain.usecase.album.GetAlbumUseCase
import com.dani.cleanarchitecture.domain.usecase.artist.GetArtistUseCase
import com.dani.cleanarchitecture.domain.usecase.songs.GetSongUseCase
import com.dani.cleanarchitecture.domain.usecase.wordinfo.GetWordInfoUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetWordUseCase(wordInfoRepository: WordInfoRepository): GetWordInfoUseCase =
        GetWordInfoUseCase(wordInfoRepository)


    @Provides
    @Singleton
    fun provideSongUseCase(songRepository: SongRepository): GetSongUseCase =
        GetSongUseCase(songRepository)

    @Provides
    @Singleton
    fun provideAlbumUseCase(albumRepository: AlbumRepository): GetAlbumUseCase =
        GetAlbumUseCase(albumRepository)

    @Provides
    @Singleton
    fun provideArtistUseCase(artistRepository: ArtistRepository): GetArtistUseCase =
        GetArtistUseCase(artistRepository)

    @Provides
    @Singleton
    fun provideWordRepository(
        wordInfoDao: WordInfoDao,
        dictionaryApi: DictionaryApi
    ): WordInfoRepository = WordInfoRepositoryImpl(dictionaryApi, wordInfoDao)


    @Provides
    @Singleton
    fun provideSongRepository(
        context: Application,
        songDao: SongDao
    ): SongRepository = SongRepositoryImpl(context, songDao)

    @Provides
    @Singleton
    fun provideAlbumRepository(
        context: Application,
        albumDao: AlbumDao
    ): AlbumRepository = AlbumRepositoryImpl(albumDao)

    @Provides
    @Singleton
    fun provideArtistRepository(
        context: Application,
        artistDao: ArtistDao
    ): ArtistRepository = ArtistRepositoryImpl(artistDao)

    @Provides
    @Singleton
    fun provideAppDatabase(context: Application): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "word_database"
    ).addTypeConverter(Converters(GsonParser(Gson())))
        .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }


    @Provides
    fun provideWordDao(appDatabase: AppDatabase): WordInfoDao = appDatabase.wordDao

    @Provides
    fun provideSongDao(appDatabase: AppDatabase): SongDao = appDatabase.songDao

    @Provides
    fun provideAlbumDao(appDatabase: AppDatabase): AlbumDao = appDatabase.albumDao

    @Provides
    fun provideArtistDao(appDatabase: AppDatabase): ArtistDao = appDatabase.artistDao

}