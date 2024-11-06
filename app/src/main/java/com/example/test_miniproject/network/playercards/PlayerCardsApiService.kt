package com.example.test_miniproject.network.playercards

import com.example.test_miniproject.model.playercards.Playercards_data
import retrofit2.http.GET

interface PlayerCardsApiService {

    @GET("v1/playercards")
    suspend fun getPlayerCards(): Playercards_data

}