package com.example.fubutube.util

import com.example.fubutube.data.db.VideoEntity
import com.example.fubutube.data.model.PexelsVideo
import com.example.fubutube.ui.home.tab.UiVideo

fun VideoEntity.toUiVideo(): UiVideo {
    return UiVideo(
        id = this.id,
        title = "Video ${this.id}",
        thumbnailUrl = this.thumbnailUrl,
        videoUrl = this.videoUrl
    )
}

fun UiVideo.toVideoEntity(): VideoEntity {
    return VideoEntity(
        id = this.id,
        thumbnailUrl = this.thumbnailUrl,
        videoUrl = this.videoUrl
    )
}


fun PexelsVideo.toUiVideo(): UiVideo? {
    val file = video_files.firstOrNull { it.quality == "hd" } ?: return null
    return UiVideo(
        id = id,
        title = "Video $id",
        thumbnailUrl = image,
        videoUrl = file.link
    )
}