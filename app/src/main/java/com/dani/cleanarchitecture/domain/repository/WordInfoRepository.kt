package com.dani.cleanarchitecture.domain.repository

import com.dani.cleanarchitecture.core.ApiStates
import com.dani.cleanarchitecture.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<ApiStates<List<WordInfo>>>
}