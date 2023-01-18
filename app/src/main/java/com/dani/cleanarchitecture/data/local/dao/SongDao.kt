package com.dani.cleanarchitecture.data.local.dao

import androidx.room.*
import com.dani.cleanarchitecture.data.local.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertSongs(songs: List<SongEntity>)

    @Delete
    fun deleteDBSong(songEntity: SongEntity)

    @Query("SELECT * FROM tblSongs")
    fun getDBSongs(): Flow<List<SongEntity>>
}