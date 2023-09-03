package com.example.giphy.data.api

import com.example.giphy.BuildConfig
import com.example.giphy.data.api.model.GifResponseObject
import com.example.giphy.data.api.model.GifsResponseObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyApi {
    companion object {
        const val BASE_URL = "https://api.giphy.com/v1/gifs/"
        const val API_KEY = BuildConfig.API_KEY
        const val DEFAULT_LIMIT = 10
    }

    @GET("trending")
    suspend fun getGifs(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ) : Response<GifsResponseObject>

    @GET("{id}")
    suspend fun getGifById(
        @Path("id") id: String,
    ) : Response<GifResponseObject>
}