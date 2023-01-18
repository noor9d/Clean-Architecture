package com.dani.cleanarchitecture.presentation.wordinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.cleanarchitecture.core.ApiStates
import com.dani.cleanarchitecture.domain.usecase.wordinfo.GetWordInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val wordInfoUseCase: GetWordInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WordInfoState())
    val state = _state.asStateFlow()
    private var searchJob: Job? = null

    fun getWordInfo(word: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            wordInfoUseCase(word).onEach { apiStates ->
                when (apiStates) {
                    is ApiStates.Loading -> {
                        _state.value = state.value.copy(
                            wordInfoItems = apiStates.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is ApiStates.Success -> {
                        _state.value = state.value.copy(
                            wordInfoItems = apiStates
                                .data ?: emptyList(),
                            isLoading = false,
                            error = ""
                        )
                    }
                    else -> {
                        _state.value = state.value.copy(
                            wordInfoItems = apiStates.data ?: emptyList(),
                            isLoading = false,
                            error = apiStates.message ?: "Unknown error"
                        )
                    }
                }

            }.launchIn(this)
        }
    }

}