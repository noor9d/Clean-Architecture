package com.dani.cleanarchitecture.domain.model

data class Artist(
    val artistName: String,
    val totalSong: Int,
    val albumId: Long,
    val artistId: Long
)