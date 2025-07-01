package com.example.fubutube.ui.home.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fubutube.data.db.VideoEntity
import com.example.fubutube.data.repository.VideoRepository
import com.example.fubutube.util.toUiVideo
import com.example.fubutube.util.toVideoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class VideoFragmentViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _videos = MutableLiveData<List<UiVideo>>()
    val videos: LiveData<List<UiVideo>> = _videos

    // 例: お気に入りIDのセットをLiveDataで持つ
    private val _favoriteIds = MutableLiveData<Set<String>>(setOf())
    val favoriteIds: LiveData<Set<String>> = _favoriteIds

    suspend fun loadMovies(key: String) : List<UiVideo> {
        val uiVideos = repository.loadMovies(key).mapNotNull { it.toUiVideo() }
        _videos.postValue(uiVideos)
        return uiVideos
    }

    suspend fun loadFavorite() {
        val favorites = repository.getAll()
        val ids = favorites.map { it.id.toString() }.toSet()
        _favoriteIds.postValue(ids)
    }

    fun toggleFavorite(video: UiVideo, newState: Boolean,key: String) {
        viewModelScope.launch {
            if (newState) {
                repository.insert(video.toVideoEntity())
            } else {
                repository.delete(video.toVideoEntity())
            }
            // DB操作後に最新のIDセットを取得して更新
            val favorites = repository.getAll()
            val ids = favorites.map { it.id.toString() }.toSet()
            _favoriteIds.postValue(ids)

            // ここで動画一覧を再取得して更新
            val updatedVideos = repository.loadMovies(key) // 必要に応じて引数を調整
            val uiVideos = updatedVideos.mapNotNull { it.toUiVideo() }
            _videos.postValue(uiVideos)
        }
    }
}