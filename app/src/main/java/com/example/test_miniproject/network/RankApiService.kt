package com.example.test_miniproject.network

import com.example.test_miniproject.model.rank_details.Ranks_data
import retrofit2.http.GET

interface RankApiService {

    @GET("v1/competitivetiers")
    suspend fun getRanks(): Ranks_data

}