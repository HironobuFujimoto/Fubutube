package com.example.fubutube.data.repository

import com.example.fubutube.data.db.VideoEntity
import com.example.fubutube.data.model.PexelsVideo

interface VideoRepository {

    suspend fun loadMovies(key: String) : List<PexelsVideo>

    suspend fun loadMovieById(id: String) : PexelsVideo

    suspend fun getAll(): List<VideoEntity>
    suspend fun getById(id: String): VideoEntity
    suspend fun insert(video: VideoEntity)
    suspend fun delete(video: VideoEntity)
}