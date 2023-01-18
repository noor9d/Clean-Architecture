package com.dani.cleanarchitecture.presentation.songs.fragment.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.dani.cleanarchitecture.R
import com.dani.cleanarchitecture.core.utils.MusicUtils
import com.dani.cleanarchitecture.databinding.AlbumItemLayoutBinding
import com.dani.cleanarchitecture.domain.model.Album

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var albumList = ArrayList<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AlbumItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albumList[position]
        holder.binding.apply {
            txtAlbumName.text = album.albumName
            txtAlbumArtist.text = album.artistName
            loadAlbumCover(album, holder)
        }
    }

    override fun getItemCount(): Int = albumList.size

    private fun loadAlbumCover(album: Album, holder: ViewHolder) {
        holder.binding.imgAlbum.load(MusicUtils.getMediaStoreAlbumCoverUri(album.albumId)) {
            crossfade(true)
            crossfade(700)
            transformations(RoundedCornersTransformation(radius = 10F))
            placeholder(R.drawable.default_album_art)
            listener(object : ImageRequest.Listener {
                override fun onError(request: ImageRequest, result: ErrorResult) {
                    super.onError(request, result)
                    holder.binding.imgAlbum.load(R.drawable.default_album_art) {
                        crossfade(true)
                        crossfade(700)
                        transformations(RoundedCornersTransformation(radius = 10F))

                    }
                }
            })


        }

    }

    fun setData(albumList: List<Album>) {
        this.albumList.clear()
        this.albumList.addAll(albumList)
        notifyDataSetChanged()
    }

     class ViewHolder(val binding: AlbumItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}