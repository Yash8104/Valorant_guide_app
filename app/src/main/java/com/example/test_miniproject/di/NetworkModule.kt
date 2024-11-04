package com.example.test_miniproject.di

import com.example.test_miniproject.network.AgentApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://valorant-api.com/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideAgentsApiService(retrofit: Retrofit) : AgentApiService{
        return retrofit.create(AgentApiService::class.java)
    }

}