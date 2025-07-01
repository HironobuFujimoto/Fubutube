package com.example.fubutube.data.repository

import com.example.fubutube.data.db.VideoDao
import com.example.fubutube.data.db.VideoEntity
import com.example.fubutube.data.model.PexelsVideo
import com.example.fubutube.data.remote.PexelsVideoService
import javax.inject.Inject

class VideoRepositoryImpl  @Inject constructor(
    private val api: PexelsVideoService,
    private val videoDao: VideoDao
) : VideoRepository {

    override suspend fun loadMovies(key: String): List<PexelsVideo> {
        return api.getVideos(key).videos
    }

    override suspend fun loadMovieById(id: String): PexelsVideo {
        return api.getVideoById(id)
    }

    override suspend fun getAll(): List<VideoEntity> = videoDao.getAll()
    override suspend fun getById(id: String): VideoEntity = videoDao.getById(id)
    override suspend fun insert(video: VideoEntity) = videoDao.insert(video)
    override suspend fun delete(video: VideoEntity) = videoDao.delete(video)

}