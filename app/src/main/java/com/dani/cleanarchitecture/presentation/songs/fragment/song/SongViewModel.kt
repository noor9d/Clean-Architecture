package com.dani.cleanarchitecture.presentation.songs.fragment.song

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.cleanarchitecture.domain.model.Song
import com.dani.cleanarchitecture.domain.usecase.songs.GetSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val songUseCase: GetSongUseCase
) : ViewModel() {

    var allSongs: MutableLiveData<List<Song>> = MutableLiveData()

    fun getDBSongs(activity: AppCompatActivity) = viewModelScope.launch {
        songUseCase(activity).onEach { songList ->
            allSongs.postValue(songList)

        }.launchIn(this)
    }

}