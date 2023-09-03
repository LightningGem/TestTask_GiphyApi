package com.example.giphy.data.di

import com.example.giphy.data.GifRepositoryImpl
import com.example.giphy.domain.GifRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindGifRepository(impl: GifRepositoryImpl) : GifRepository
}