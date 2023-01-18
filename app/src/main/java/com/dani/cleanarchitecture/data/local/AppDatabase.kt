package com.dani.cleanarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dani.cleanarchitecture.data.local.dao.AlbumDao
import com.dani.cleanarchitecture.data.local.dao.ArtistDao
import com.dani.cleanarchitecture.data.local.dao.SongDao
import com.dani.cleanarchitecture.data.local.dao.WordInfoDao
import com.dani.cleanarchitecture.data.local.entity.SongEntity
import com.dani.cleanarchitecture.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class, SongEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val wordDao: WordInfoDao

    abstract val songDao: SongDao

    abstract val albumDao: AlbumDao

    abstract val artistDao: ArtistDao
}