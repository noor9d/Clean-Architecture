package com.dani.cleanarchitecture.domain.usecase.songs

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.dani.cleanarchitecture.data.local.entity.SongEntity
import com.dani.cleanarchitecture.domain.model.Song
import com.dani.cleanarchitecture.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetSongUseCase @Inject constructor(
    private val songRepository: SongRepository
) {

    suspend operator fun invoke(activity: AppCompatActivity): Flow<List<Song>> {
        songRepository.getAllDeviceSongs(activity)
        return songRepository.getDBSongs().map { it.map { songEntity -> songEntity.toSong() } }
    }
}