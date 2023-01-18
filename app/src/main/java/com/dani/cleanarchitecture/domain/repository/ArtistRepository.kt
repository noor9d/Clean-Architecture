package com.dani.cleanarchitecture.domain.repository

import com.dani.cleanarchitecture.domain.model.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {
    fun getAllArtist(): Flow<List<Artist>>
}