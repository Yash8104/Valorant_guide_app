package com.example.test_miniproject.network.ranks

import android.util.Log
import com.example.test_miniproject.model.rank_details.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RankRepository @Inject constructor(
    private val apiService: RankApiService
){

    suspend fun getRanks(): Data?{
        return try {
            Log.e("test","im in get agents")
            val response = apiService.getRanks()
            Log.e("resonse",response.status.toString())
            if(response.status == 200){
                response.data.last()
            }else{
                null
            }
        }catch (e : Exception){
            e.printStackTrace()
            null
        }
    }

}