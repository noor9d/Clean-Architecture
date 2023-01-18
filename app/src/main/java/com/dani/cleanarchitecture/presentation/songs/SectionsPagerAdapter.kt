package com.dani.cleanarchitecture.presentation.songs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dani.cleanarchitecture.presentation.songs.fragment.album.AlbumFragment
import com.dani.cleanarchitecture.presentation.songs.fragment.artist.ArtistFragment
import com.dani.cleanarchitecture.presentation.songs.fragment.song.SongFragment

class SectionsPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SongFragment()

            }
            1 -> {
                AlbumFragment()

            }

            else -> {
                ArtistFragment()

            }

        }

    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> {
                "Songs"

            }
            1 -> {
                "Albums"

            }
            else -> {
                "Artist"
            }
        }
    }

    override fun getCount(): Int {

        return 3
    }


}