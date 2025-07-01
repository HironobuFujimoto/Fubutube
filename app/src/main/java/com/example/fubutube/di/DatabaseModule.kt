package com.example.fubutube.di

import android.content.Context
import androidx.room.Room
import com.example.fubutube.AppDatabase
import com.example.fubutube.data.db.VideoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "fubu_db"
        ).build()
    }

    @Provides
    fun provideVideDao(database: AppDatabase): VideoDao {
        return database.videoDao()
    }
}