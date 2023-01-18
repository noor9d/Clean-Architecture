package com.dani.cleanarchitecture.data.repository

import com.dani.cleanarchitecture.data.local.dao.ArtistDao
import com.dani.cleanarchitecture.domain.model.Artist
import com.dani.cleanarchitecture.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val artistDao: ArtistDao
) : ArtistRepository {
    override fun getAllArtist(): Flow<List<Artist>> = artistDao.getAllArtist()
}