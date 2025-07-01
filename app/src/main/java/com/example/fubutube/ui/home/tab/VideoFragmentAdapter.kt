package com.example.fubutube.ui.home.tab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.media3.ui.PlayerView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fubutube.R
import com.example.fubutube.ui.home.HomeFragmentDirections

class VideoFragmentAdapter(private var uiVideos: List<UiVideo>,
                           private var favoriteIds: Set<String>,
                           private val onFavoriteClick: (UiVideo, Boolean) -> Unit) : RecyclerView.Adapter<VideoFragmentAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        var video = uiVideos[position]
        holder.id.text = video.id.toString()
        Glide.with(holder.itemView.context)
            .load(video.thumbnailUrl)
            .centerCrop()
            .into(holder.imageView)
        val isFavorite = favoriteIds.contains(video.id.toString())
        holder.bind(video, isFavorite, onFavoriteClick)
    }

    override fun getItemCount(): Int {
        return uiVideos.size
    }

    fun updateMovies(uiVideos: List<UiVideo>) {
        this.uiVideos = uiVideos
        notifyDataSetChanged()
    }
    fun updateFavoriteIds(favoriteIds: Set<String>) {
        this.favoriteIds = favoriteIds
        notifyDataSetChanged() // 必要に応じて差分更新を考慮してもよい
    }

    class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val imageView: ImageView = itemView.findViewById(R.id.thumbnail)
        val favoriteButton: AppCompatImageButton = itemView.findViewById(R.id.favoriteButton)

        init {
            itemView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id.text.toString())
                itemView.findNavController().navigate(action)
            }
        }

        fun bind(video: UiVideo, isFavorite: Boolean, onFavoriteClick: (UiVideo, Boolean) -> Unit) {

            updateFavoriteIcon(isFavorite)

            favoriteButton.setOnClickListener {
                val newState = !isFavorite
                updateFavoriteIcon(newState)
                onFavoriteClick(video, newState)
            }
        }

        private fun updateFavoriteIcon(isFavorite: Boolean) {
            val iconRes = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border
            favoriteButton.setImageResource(iconRes)
        }
    }

}