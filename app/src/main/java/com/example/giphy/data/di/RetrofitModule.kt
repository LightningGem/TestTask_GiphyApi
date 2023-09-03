package com.example.giphy.data.di

import com.example.giphy.data.api.GiphyApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().apply {
                    this.url(
                        chain.request().url().newBuilder()
                            .addQueryParameter("api_key", GiphyApi.API_KEY)
                            .build()
                    )
                }.build()
            )
        }
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(GiphyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()

    @Singleton
    @Provides
    fun providesIntegrateApi(retrofit: Retrofit): GiphyApi = retrofit.create(GiphyApi::class.java)
}