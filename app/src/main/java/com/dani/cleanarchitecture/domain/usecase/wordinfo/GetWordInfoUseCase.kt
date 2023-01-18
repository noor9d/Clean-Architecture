package com.dani.cleanarchitecture.domain.usecase.wordinfo

import com.dani.cleanarchitecture.core.ApiStates
import com.dani.cleanarchitecture.domain.model.WordInfo
import com.dani.cleanarchitecture.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWordInfoUseCase @Inject constructor(private val wordInfoRepository: WordInfoRepository) {
    operator fun invoke(word: String): Flow<ApiStates<List<WordInfo>>> {
        return if (word.isBlank()) {
            flow { }
        } else {

            wordInfoRepository.getWordInfo(word)
        }

    }
}