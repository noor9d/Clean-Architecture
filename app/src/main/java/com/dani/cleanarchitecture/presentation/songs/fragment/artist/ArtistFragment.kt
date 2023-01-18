package com.dani.cleanarchitecture.presentation.songs.fragment.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dani.cleanarchitecture.databinding.FragmentArtistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment : Fragment() {

    private var binding: FragmentArtistBinding? = null
    private val artistViewModel: ArtistViewModel by viewModels()
    private var artistAdapter: ArtistAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtistBinding.inflate(inflater, container, false)
        initViews()
        return binding?.root
    }

    private fun initViews() {
        artistAdapter = ArtistAdapter()
        binding?.artistRecyclerView?.adapter = artistAdapter

        artistViewModel.artistList.observe(viewLifecycleOwner) { albumList ->
            artistAdapter?.setData(albumList)
        }
    }

}