package com.dani.cleanarchitecture.data.remote.dto

import com.dani.cleanarchitecture.data.local.entity.WordInfoEntity


data class WordInfoDto(
    var meanings: List<MeaningDto>?,
    val origin: String?,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>?,
    val word: String?
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = if (meanings != null) meanings?.map { it.toMeaning() }!! else emptyList(),
            origin = origin ?: "",
            phonetic = phonetic ?: "",
            word = word ?: ""
        )
    }
}