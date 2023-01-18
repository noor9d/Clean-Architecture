package com.dani.cleanarchitecture.data.repository

import com.dani.cleanarchitecture.data.local.dao.AlbumDao
import com.dani.cleanarchitecture.domain.model.Album
import com.dani.cleanarchitecture.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao
) : AlbumRepository {
    override fun getAllAlbums(): Flow<List<Album>> = albumDao.getAllAlbums()
}