package com.example.test_miniproject.network.playercards

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import com.example.test_miniproject.model.playercards.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerCardsRepository @Inject constructor(
    private val apiService: PlayerCardsApiService
){

    var error_message = mutableStateOf("")

    fun getErrorMessage(): String{
        return error_message.value
    }

    suspend fun getPlayerCards(): List<Data> {
        return try {
            Log.e("test","im in get player cards")
            val response = apiService.getPlayerCards()
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

    suspend fun downloadImage(imageUrl: String) : Bitmap?{

        try {
            val response = apiService.downloadPlayerCard(imageUrl)
            if(response.isSuccessful){
                response.body()?.let {
                        responseBody ->
                    val inputStream = responseBody.byteStream()
                    val bitmap = BitmapFactory.decodeStream(inputStream)

                    Log.e("Download Image", "It might be working guys!!!")
                    Log.e("bitmap details",bitmap.toString())
                    return bitmap
                }
                return null

            }else{
                Log.e("Download","Failed to download image : ${response.message()}")
                return null
            }
        }catch (e : Exception){
            e.printStackTrace()
            return null
        }


    }

}