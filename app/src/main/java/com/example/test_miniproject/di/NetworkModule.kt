package com.example.test_miniproject.di

import com.example.test_miniproject.network.agents.AgentApiService
import com.example.test_miniproject.network.buddies.BuddiesApiService
import com.example.test_miniproject.network.playercards.PlayerCardsApiService
import com.example.test_miniproject.network.ranks.RankApiService
import com.example.test_miniproject.network.sprays.SprayApiService
import com.example.test_miniproject.network.weapons.WeaponApiService
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
    fun provideAgentsApiService(retrofit: Retrofit) : AgentApiService {
        return retrofit.create(AgentApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeaponsApiService(retrofit: Retrofit) : WeaponApiService {
        return retrofit.create(WeaponApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRankApiService(retrofit: Retrofit) : RankApiService {
        return retrofit.create(RankApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePlayerCardApiService(retrofit: Retrofit) : PlayerCardsApiService {
        return retrofit.create(PlayerCardsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSprayApiServiceApiService(retrofit: Retrofit) : SprayApiService {
        return retrofit.create(SprayApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideBuddiesApiServiceApiService(retrofit: Retrofit) : BuddiesApiService {
        return retrofit.create(BuddiesApiService::class.java)
    }



}