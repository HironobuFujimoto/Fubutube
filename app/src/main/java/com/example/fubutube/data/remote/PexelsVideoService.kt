package com.example.fubutube.data.remote

import com.example.fubutube.data.model.PexelsVideoResponse
import com.example.fubutube.data.model.PexelsVideo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsVideoService {

    @GET("videos/search")
    suspend fun getVideos(
        @Query("query") query: String
    ): PexelsVideoResponse

    @GET("videos/videos/{id}")
    suspend fun getVideoById(
        @Path("id") id: String
    ): PexelsVideo

}