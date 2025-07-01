package com.example.fubutube.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface VideoDao {
    @Query("SELECT * FROM video")
    suspend fun getAll(): List<VideoEntity>

    @Query("SELECT * FROM video WHERE id = :id")
    suspend fun getById(id : String): VideoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(video: VideoEntity)

    @Delete
    suspend fun delete(video: VideoEntity)

}