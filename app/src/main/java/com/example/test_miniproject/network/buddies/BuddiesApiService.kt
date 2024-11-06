package com.example.test_miniproject.network.buddies

import com.example.test_miniproject.model.buddies_detail.Buddies_data
import retrofit2.http.GET

interface BuddiesApiService {

    @GET("v1/buddies")
    suspend fun getBuddies() : Buddies_data

}