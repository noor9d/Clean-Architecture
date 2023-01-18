package com.dani.cleanarchitecture.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani.cleanarchitecture.domain.model.Artist
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    @Query("SELECT DISTINCT artistName,count(songPrimaryId) as totalSong,albumId,artistId  FROM tblSongs GROUP BY artistName")
    fun getAllArtist(): Flow<List<Artist>>
}