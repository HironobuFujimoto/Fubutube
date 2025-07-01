package com.example.fubutube.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fubutube.data.repository.VideoRepository
import com.example.fubutube.ui.home.tab.UiVideo
import com.example.fubutube.util.toUiVideo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _video = MutableLiveData<UiVideo>()
    val video: LiveData<UiVideo> = _video

    // 例: お気に入りIDのセットをLiveDataで持つ
    private val _favoriteIds = MutableLiveData<String>()
    val favoriteIds: LiveData<String> = _favoriteIds

    suspend fun loadMovie(id: String) : UiVideo {
        val video = repository.loadMovieById(id)
        val uiVideo = video.toUiVideo()
        _video.postValue(uiVideo!!)
        return uiVideo
    }

    suspend fun loadFavorite(id: String) {
        val favorites = repository.getById(id)
        val ids = favorites.id.toString()
        _favoriteIds.postValue(ids)
    }

}