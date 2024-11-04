package com.example.test_miniproject.network

import com.example.test_miniproject.model.agent_details.Agents_data
import retrofit2.http.GET

interface AgentApiService {

    @GET("v1/agents?isPlayableCharacter=true")
    suspend fun getAgents(): Agents_data

}