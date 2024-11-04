package com.example.test_miniproject.network

import android.util.Log
import com.example.test_miniproject.model.agent_details.Agents_data
import com.example.test_miniproject.model.agent_details.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AgentsRepository @Inject constructor(
    private val apiService: AgentApiService
) {

    suspend fun getAgents(): List<Data> {
        return try {
            Log.e("test","im in get agents")
            val response = apiService.getAgents()
            Log.e("resonse",response.status.toString())
            if(response.status == 200){
                response.data
            }else{
                emptyList()
            }
        }catch (e : Exception){
            e.printStackTrace()
            emptyList()
        }
    }


}