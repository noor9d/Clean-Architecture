package com.dani.cleanarchitecture.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani.cleanarchitecture.domain.model.Album
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Query("SELECT DISTINCT albumName,count(albumName) as totalSong,albumId,artistName FROM tblSongs GROUP BY albumName")
    fun getAllAlbums(): Flow<List<Album>>
}