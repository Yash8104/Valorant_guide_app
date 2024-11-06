package com.example.test_miniproject.network.buddies

import android.util.Log
import com.example.test_miniproject.model.buddies_detail.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuddiesRepository @Inject constructor(
    private val buddiesApiService: BuddiesApiService
){

    suspend fun getBuddies(): List<Data> {
        return try {
            Log.e("test","im in get buddies")
            val response = buddiesApiService.getBuddies()
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