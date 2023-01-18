package com.dani.cleanarchitecture.presentation.songs.fragment.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.cleanarchitecture.domain.model.Artist
import com.dani.cleanarchitecture.domain.usecase.artist.GetArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val getArtistUseCase: GetArtistUseCase
) : ViewModel() {
    init {
        getAllArtist()
    }

    val artistList = MutableLiveData<List<Artist>>()


    private fun getAllArtist() = viewModelScope.launch {

        getArtistUseCase().onEach { artists ->
            artistList.postValue(artists)
        }.launchIn(this)
    }
}