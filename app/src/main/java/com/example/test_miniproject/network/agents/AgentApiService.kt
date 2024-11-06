package com.example.test_miniproject.network.agents

import com.example.test_miniproject.model.agent_details.Agent_data
import com.example.test_miniproject.model.agent_details.Agents_data
import retrofit2.http.GET
import retrofit2.http.Path

interface AgentApiService {

    @GET("v1/agents?isPlayableCharacter=true")
    suspend fun getAgents(): Agents_data

    @GET("v1/agents/{agentUuid}")
    suspend fun getAgent(@Path("agentUuid") agentUuid : String) : Agent_data

}