package com.dani.cleanarchitecture.presentation.songs.fragment.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dani.cleanarchitecture.databinding.FragmentAlbumBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment() {

    private var binding: FragmentAlbumBinding? = null
    private val albumViewModel: AlbumViewModel by viewModels()
    private var albumAdapter: AlbumAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        initViews()
        return binding?.root
    }

    private fun initViews() {
        albumAdapter = AlbumAdapter()
        binding?.albumRecyclerView?.adapter = albumAdapter

        albumViewModel.albumList.observe(viewLifecycleOwner) { albumList ->
            albumAdapter?.setData(albumList)
        }
    }
}