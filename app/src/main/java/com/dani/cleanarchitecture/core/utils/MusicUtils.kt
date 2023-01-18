package com.dani.cleanarchitecture.core.utils

import android.content.ContentUris
import android.net.Uri
import androidx.core.net.toUri

object MusicUtils {
    @JvmStatic
    fun getMediaStoreAlbumCoverUri(albumId: Long): Uri {
        val sArtworkUri = "content://media/external/audio/albumart".toUri()
        return ContentUris.withAppendedId(sArtworkUri, albumId)
    }
}