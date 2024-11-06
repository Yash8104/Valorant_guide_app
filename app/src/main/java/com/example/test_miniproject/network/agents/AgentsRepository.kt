package com.example.test_miniproject.network.agents

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.test_miniproject.model.agent_details.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AgentsRepository @Inject constructor(
    private val apiService: AgentApiService
) {

    var error_message = mutableStateOf("")

    fun getErrorMessage(): String{
        return error_message.value
    }


    suspend fun getAgents(): List<Data> {
        return try {
            Log.e("test","im in get agents")
            val response = apiService.getAgents()
            Log.e("resonse",response.status.toString())
            if(response.status == 200){
                response.data
            }else{
                error_message.value = "Response code is not 200."
                emptyList()
            }
        }catch (e : Exception){
            e.printStackTrace()
            error_message.value = e.message.toString()
            emptyList()
        }
    }

    suspend fun getAgent(uuid: String): Data?{
        return try {

            Log.e("repo","getAgent() function block")
            val response = apiService.getAgent(uuid)
            Log.e("response",response.status.toString())
            if (response.status == 200){
                response.data
            }else{
                error_message.value = "Response code is not 200."
                null
            }

        }catch (e: Exception){
            e.printStackTrace()
            error_message.value = e.message.toString()
            null
        }
    }


}