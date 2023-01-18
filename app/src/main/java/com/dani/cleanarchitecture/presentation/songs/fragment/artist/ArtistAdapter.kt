package com.dani.cleanarchitecture.presentation.songs.fragment.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.dani.cleanarchitecture.R
import com.dani.cleanarchitecture.core.utils.MusicUtils.getMediaStoreAlbumCoverUri
import com.dani.cleanarchitecture.databinding.AlbumItemLayoutBinding
import com.dani.cleanarchitecture.domain.model.Artist
import com.dani.cleanarchitecture.presentation.songs.fragment.album.AlbumAdapter

class ArtistAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    private var artistList = ArrayList<Artist>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.ViewHolder {
        return AlbumAdapter.ViewHolder(
            AlbumItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        val artist = artistList[position]
        holder.binding.apply {
            txtAlbumName.text = artist.artistName
            txtAlbumArtist.text = "${artist.totalSong} Songs"
            loadAlbumCover(artist, holder)
        }
    }

    private fun loadAlbumCover(artist: Artist, holder: AlbumAdapter.ViewHolder) {

        holder.binding.imgAlbum.load(

            getMediaStoreAlbumCoverUri(artist.albumId)

        ) {
            crossfade(true)
            crossfade(700)
            transformations(RoundedCornersTransformation(radius = 10F))
            placeholder(R.drawable.default_artist_art)
            listener(object : ImageRequest.Listener {
                override fun onError(request: ImageRequest, result: ErrorResult) {
                    super.onError(request, result)
                    holder.binding.imgAlbum.load(R.drawable.default_artist_art) {
                        crossfade(true)
                        crossfade(700)
                        transformations(RoundedCornersTransformation(radius = 10F))

                    }
                }
            })
        }

    }


    fun setData(artistList: List<Artist>) {
        this.artistList.clear()
        this.artistList.addAll(artistList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = artistList.size

}
