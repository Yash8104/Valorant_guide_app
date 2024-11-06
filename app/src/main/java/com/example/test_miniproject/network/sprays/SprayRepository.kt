package com.example.test_miniproject.network.sprays

import android.util.Log
import com.example.test_miniproject.model.spray_details.Data
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SprayRepository @Inject constructor(
    private val apiService: SprayApiService
){

    suspend fun getSprays(): List<Data> {
        return try {
            Log.e("test","im in get sprays")
            val response = apiService.getSprays()
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