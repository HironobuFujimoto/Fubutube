package com.example.fubutube.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
data class VideoEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "thumbnail_url")
    var thumbnailUrl: String = "",

    @ColumnInfo(name = "video_url")
    var videoUrl: String,


)