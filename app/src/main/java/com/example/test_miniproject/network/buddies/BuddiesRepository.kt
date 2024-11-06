package com.example.test_miniproject.network.buddies

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.test_miniproject.model.buddies_detail.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuddiesRepository @Inject constructor(
    private val buddiesApiService: BuddiesApiService
){

    var error_message = mutableStateOf("")

    fun getErrorMessage(): String{
        return error_message.value
    }

    suspend fun getBuddies(): List<Data> {
        return try {
            Log.e("test","im in get buddies")
            val response = buddiesApiService.getBuddies()
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

}