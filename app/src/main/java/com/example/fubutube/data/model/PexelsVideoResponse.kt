package com.example.fubutube.data.model



data class PexelsVideoResponse(
    val page: Int,
    val per_page: Int,
    val total_results: Int,
    val videos: List<PexelsVideo>
)