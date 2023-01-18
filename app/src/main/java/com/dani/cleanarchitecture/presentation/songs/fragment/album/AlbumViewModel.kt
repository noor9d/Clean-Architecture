package com.dani.cleanarchitecture.presentation.songs.fragment.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.cleanarchitecture.domain.model.Album
import com.dani.cleanarchitecture.domain.usecase.album.GetAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbumUseCase: GetAlbumUseCase
) : ViewModel() {

    init {
        getAlbum()
    }

    val albumList = MutableLiveData<List<Album>>()

    private fun getAlbum() = viewModelScope.launch {
        getAlbumUseCase().onEach { album ->
            albumList.postValue(album)
        }.launchIn(this)
    }
}