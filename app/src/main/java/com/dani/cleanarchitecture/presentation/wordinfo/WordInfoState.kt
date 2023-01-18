package com.dani.cleanarchitecture.presentation.wordinfo

import com.dani.cleanarchitecture.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)