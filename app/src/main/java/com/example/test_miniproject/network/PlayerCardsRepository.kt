package com.example.test_miniproject.network

import android.util.Log
import com.example.test_miniproject.model.playercards.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerCardsRepository @Inject constructor(
    private val apiService: PlayerCardsApiService
){

    suspend fun getPlayerCards(): List<Data> {
        return try {
            Log.e("test","im in get player cards")
            val response = apiService.getPlayerCards()
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