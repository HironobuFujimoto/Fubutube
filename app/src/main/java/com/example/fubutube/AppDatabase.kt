package com.example.fubutube

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fubutube.data.db.VideoDao
import com.example.fubutube.data.db.VideoEntity

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao

}