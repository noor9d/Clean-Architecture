package com.dani.cleanarchitecture.domain.repository

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.dani.cleanarchitecture.data.local.entity.SongEntity
import com.dani.cleanarchitecture.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {

    suspend fun getAllDeviceSongs(activity: AppCompatActivity)

    fun getDBSongs(): Flow<List<SongEntity>>

    fun deleteSong(song: Song)

}