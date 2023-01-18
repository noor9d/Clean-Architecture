package com.dani.cleanarchitecture.presentation.songs.fragment.song

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.dani.cleanarchitecture.R
import com.dani.cleanarchitecture.core.utils.MusicUtils.getMediaStoreAlbumCoverUri
import com.dani.cleanarchitecture.databinding.SongItemLayoutBinding
import com.dani.cleanarchitecture.domain.model.Song

class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private var songList = ArrayList<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SongItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songList[position]
        holder.binding.apply {
            txtSongName.text = song.title
            txtSongAlbum.text = song.albumName
            loadAlbumCover(song, holder)
        }
    }

    override fun getItemCount(): Int = songList.size

    private fun loadAlbumCover(song: Song, holder: ViewHolder) {
        holder.binding.imgSong.load(getMediaStoreAlbumCoverUri(song.albumId)) {
            crossfade(true)
            crossfade(700)
            transformations(RoundedCornersTransformation(radius = 10F))
            placeholder(R.drawable.default_audio_art)
            listener(object : ImageRequest.Listener {
                override fun onError(request: ImageRequest, result: ErrorResult) {
                    super.onError(request, result)
                    holder.binding.imgSong.load(R.drawable.default_audio_art) {
                        crossfade(true)
                        crossfade(700)
                        transformations(RoundedCornersTransformation(radius = 10F))

                    }
                }
            })


        }

    }

    fun setData(songList: List<Song>) {
        this.songList.clear()
        this.songList.addAll(songList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: SongItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}