package com.example.fubutube.data.model

data class PexelsVideo(
    val id: Int,
    val url: String,
    val image: String,
    val video_files: List<PexelsVideoFile>
)