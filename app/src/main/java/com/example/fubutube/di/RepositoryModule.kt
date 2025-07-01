package com.example.fubutube.di

import com.example.fubutube.data.repository.VideoRepository
import com.example.fubutube.data.repository.VideoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(
        impl: VideoRepositoryImpl
    ): VideoRepository
}