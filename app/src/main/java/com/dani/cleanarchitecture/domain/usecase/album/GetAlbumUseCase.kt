package com.dani.cleanarchitecture.domain.usecase.album

import com.dani.cleanarchitecture.domain.repository.AlbumRepository
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(
    private val albumRepositoryImpl: AlbumRepository
) {

    operator fun invoke() = albumRepositoryImpl.getAllAlbums()
}