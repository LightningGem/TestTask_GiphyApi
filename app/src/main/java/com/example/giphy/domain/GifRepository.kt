package com.example.giphy.domain

import androidx.paging.PagingData
import com.example.giphy.domain.model.Gif
import kotlinx.coroutines.flow.Flow

interface GifRepository {
    fun gifsFlow() : Flow<PagingData<Gif>>
    suspend fun getGif(id: String) : Result<Gif>
}