package com.dani.cleanarchitecture.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dani.cleanarchitecture.domain.model.Song

@Entity(tableName = "tblSongs")
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    val songPrimaryId: Long = 0L,
    val id: Long,
    val title: String,
    val trackNumber: Int,
    val year: Int,
    val duration: Long,
    val data: String,
    val dateModified: Long,
    var albumId: Long,
    val albumName: String,
    val artistId: Long,
    val artistName: String,
    val composer: String?,
    val albumArtist: String?,
    val folderName: String?,
    val count: Int = 0,
    var isFav: Boolean = false
) {

    fun toSong(): Song {
        return Song(
            songPrimaryId,
            id,
            title,
            trackNumber,
            year,
            duration,
            data,
            dateModified,
            albumId,
            albumName,
            artistId,
            artistName,
            composer,
            albumArtist,
            folderName
        )
    }
}