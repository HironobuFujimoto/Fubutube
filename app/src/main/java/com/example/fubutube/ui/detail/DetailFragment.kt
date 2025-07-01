package com.example.fubutube.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.fragment.navArgs
import com.example.fubutube.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_detail, container, false)
        exoPlayer = ExoPlayer.Builder(view.context).build()
        val playerView = view.findViewById<PlayerView>(R.id.player)
        playerView.player = exoPlayer

        val args: DetailFragmentArgs by navArgs()
        val videoId = args.videoId
        viewModel.viewModelScope.launch {
            var uiVideo = withContext(Dispatchers.IO) {
                viewModel.loadMovie(videoId)
            }
            // メインスレッドに戻って ExoPlayer操作
            withContext(Dispatchers.Main) {
                exoPlayer.setMediaItem(MediaItem.fromUri(uiVideo.videoUrl))
                exoPlayer.prepare()
                exoPlayer.playWhenReady = true
            }
        }

        return view
    }
}