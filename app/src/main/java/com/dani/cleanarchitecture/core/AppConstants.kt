package com.dani.cleanarchitecture.core

import android.provider.BaseColumns
import android.provider.MediaStore


object AppConstants {
    const val IS_MUSIC =
        MediaStore.Audio.AudioColumns.IS_MUSIC + "=1" + " AND " + MediaStore.Audio.AudioColumns.TITLE + " != ''"

    val baseProjection = arrayOf(
        BaseColumns._ID, // 0
        MediaStore.Audio.AudioColumns.TITLE, // 1
        MediaStore.Audio.AudioColumns.TRACK, // 2
        MediaStore.Audio.AudioColumns.YEAR, // 3
        MediaStore.Audio.AudioColumns.DURATION, // 4
        DATA, // 5
        MediaStore.Audio.AudioColumns.DATE_MODIFIED, // 6
        MediaStore.Audio.AudioColumns.ALBUM_ID, // 7
        MediaStore.Audio.AudioColumns.ALBUM, // 8
        MediaStore.Audio.AudioColumns.ARTIST_ID, // 9
        MediaStore.Audio.AudioColumns.ARTIST, // 10
        MediaStore.Audio.AudioColumns.COMPOSER, // 11
        ALBUM_ARTIST // 12
    )

}

const val DATA = "_data"
const val ALBUM_ARTIST = "album_artist"
const val IS_FIRST_TIME = "is_first_time"