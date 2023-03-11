package com.codinginflow.imagesearch

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.unsplash.com"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideUnsplashService(retrofit: Retrofit) = retrofit.create(UnsplashService::class.java)
}