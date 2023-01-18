package com.dani.cleanarchitecture.domain.repository

import com.dani.cleanarchitecture.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    fun getAllAlbums(): Flow<List<Album>>
}