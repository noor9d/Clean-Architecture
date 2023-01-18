package com.dani.cleanarchitecture.presentation.songs.fragment.song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dani.cleanarchitecture.core.extension.isAlive
import com.dani.cleanarchitecture.databinding.FragmentSongBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongFragment : Fragment() {

    private var binding: FragmentSongBinding? = null
    private val songViewModel: SongViewModel by viewModels()
    private var songAdapter: SongAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)
        initViews()
        return binding?.root
    }

    private fun initViews() {
        songAdapter = SongAdapter()
        binding?.songRecyclerView?.adapter = songAdapter

        isAlive {
            songViewModel
                .getDBSongs(it)
        }
        songViewModel.allSongs.observe(viewLifecycleOwner) { songList ->
            songAdapter?.setData(songList)
        }

    }
}