package com.example.test_miniproject.network.sprays

import com.example.test_miniproject.model.spray_details.Sprays_data
import retrofit2.http.GET

interface SprayApiService {

    @GET("v1/sprays")
    suspend fun getSprays(): Sprays_data

}