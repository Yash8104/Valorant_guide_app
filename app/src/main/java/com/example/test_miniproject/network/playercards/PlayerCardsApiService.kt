package com.example.test_miniproject.network.playercards

import com.example.test_miniproject.model.playercards.Playercards_data
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PlayerCardsApiService {

    @GET("v1/playercards")
    suspend fun getPlayerCards(): Playercards_data

    @GET
    suspend fun downloadPlayerCard(@Url imageUrl: String): Response<ResponseBody>

}