package com.dani.cleanarchitecture.domain.usecase.artist

import com.dani.cleanarchitecture.domain.repository.ArtistRepository
import javax.inject.Inject

class GetArtistUseCase @Inject constructor(
    private val artistRepository: ArtistRepository
) {
    operator fun invoke() = artistRepository.getAllArtist()
}