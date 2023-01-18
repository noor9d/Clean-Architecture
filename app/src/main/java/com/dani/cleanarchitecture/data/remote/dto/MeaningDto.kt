package com.dani.cleanarchitecture.data.remote.dto

import com.dani.cleanarchitecture.domain.model.Meaning


data class MeaningDto(
    var definitions: List<DefinitionDto>?,
    val partOfSpeech: String?
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = if (definitions != null) definitions?.map { it.toDefinition() }!! else emptyList(),
            partOfSpeech = partOfSpeech ?: ""
        )
    }
}