package com.dani.cleanarchitecture.data.repository

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.database.getStringOrNull
import androidx.lifecycle.lifecycleScope
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.dani.cleanarchitecture.R
import com.dani.cleanarchitecture.core.AppConstants
import com.dani.cleanarchitecture.core.AppConstants.baseProjection
import com.dani.cleanarchitecture.core.DATA
import com.dani.cleanarchitecture.core.utils.VersionUtils
import com.dani.cleanarchitecture.data.local.dao.SongDao
import com.dani.cleanarchitecture.data.local.entity.SongEntity
import com.dani.cleanarchitecture.domain.model.Song
import com.dani.cleanarchitecture.domain.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val context: Context,
    private val songDao: SongDao
) :
    SongRepository {

    override suspend fun getAllDeviceSongs(activity: AppCompatActivity) {

        val obj = object : LoaderManager.LoaderCallbacks<Cursor> {
            override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
                val selectionFinal = AppConstants.IS_MUSIC
                val uri = if (VersionUtils.hasQ()) {
                    MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
                } else {
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }


                return CursorLoader(
                    activity,
                    uri,
                    baseProjection,
                    selectionFinal,
                    null,
                    MediaStore.Audio.Media.DEFAULT_SORT_ORDER
                )
            }

            override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor?) {
                val result = songs(cursor)

                activity.lifecycleScope.launch(Dispatchers.IO) {
                    songDao.insertSongs(result)
                }


                LoaderManager.getInstance(activity).destroyLoader(1)

            }

            override fun onLoaderReset(loader: Loader<Cursor>) {
            }
        }
        LoaderManager.getInstance(activity).initLoader(
            1,
            null,
            obj
        )
    }

    override fun getDBSongs(): Flow<List<SongEntity>> = songDao.getDBSongs()

    override fun deleteSong(song: Song) {
        songDao.deleteDBSong(song.toSongEntity())
    }

    fun songs(cursor: Cursor?): List<SongEntity> {
        val songs = arrayListOf<SongEntity>()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                songs.add(getSongFromCursorImpl(cursor))
            } while (cursor.moveToNext())
        }
        cursor?.close()
        return songs
    }

    private fun getSongFromCursorImpl(
        cursor: Cursor
    ): SongEntity {
        val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns._ID))
        val title =
            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE))
        val trackNumber =
            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TRACK))
        val year = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.YEAR))
        val duration =
            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DURATION))
        val data = cursor.getString(cursor.getColumnIndexOrThrow(DATA))
        val dateModified =
            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATE_MODIFIED))
        val albumId =
            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM_ID))
        val albumName =
            cursor.getStringOrNull(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM))
        val artistId =
            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST_ID))
        val artistName =
            cursor.getStringOrNull(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST))
        val composer =
            cursor.getStringOrNull(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.COMPOSER))
        val albumArtist = cursor.getStringOrNull(cursor.getColumnIndexOrThrow("album_artist"))
        val folderName = if (File(data).parentFile!!.name.equals("0")) {
            context.resources.getString(R.string.root)
        } else {
            File(data).parentFile!!.name
        }

        return SongEntity(
            id = id,
            title = title,
            trackNumber = trackNumber,
            year = year,
            duration = duration,
            data = data,
            dateModified = dateModified,
            albumId = albumId,
            albumName = albumName ?: "",
            artistId = artistId,
            artistName = artistName ?: "",
            composer = composer ?: "",
            albumArtist = albumArtist ?: "",
            folderName = folderName ?: ""
        )
    }
}