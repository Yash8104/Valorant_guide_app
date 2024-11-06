package com.example.test_miniproject.network.ranks

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.test_miniproject.model.rank_details.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RankRepository @Inject constructor(
    private val apiService: RankApiService
){

    var error_message = mutableStateOf("")

    fun getErrorMessage(): String{
        return error_message.value
    }

    suspend fun getRanks(): Data?{
        return try {
            Log.e("test","im in get agents")
            val response = apiService.getRanks()
            Log.e("resonse",response.status.toString())
            if(response.status == 200){
                response.data.last()
            }else{
                error_message.value = "Response code is not 200."
                null
            }
        }catch (e : Exception){
            e.printStackTrace()
            error_message.value = e.message.toString()
            null
        }
    }

}