package com.example.test_miniproject.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.playercards.Data
import com.example.test_miniproject.network.playercards.PlayerCardsRepository
import com.example.test_miniproject.network.utility.saveBitmapToGallery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerCardsViewModel @Inject constructor(
    private val repository: PlayerCardsRepository
): ViewModel(){


    val bitmap = mutableStateOf<Bitmap?>(null)

    val cards = mutableStateOf<List<Data>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf(false)
    val error_msg = mutableStateOf("")

    init {
        fetchCards()
    }

    private fun fetchCards(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                cards.value = repository.getPlayerCards()

                if(cards.value.isEmpty()){
                    error.value = true
                    error_msg.value = repository.getErrorMessage()

                }else{
                    error.value = false
                }

            }catch (e: Exception){
                e.printStackTrace()
                error.value = true
                error_msg.value = e.message.toString()
            }finally {
                isLoading.value = false
            }
        }
    }


    fun downloadImage(context : Context, imageUrl : String){

        viewModelScope.launch {
            try {
                bitmap.value = repository.downloadImage(imageUrl)
                Log.e("testing in viewmodel", bitmap.value.toString())
                if (bitmap.value != null) {
                    // Call a function to save the image to gallery
                    saveBitmapToGallery(context, bitmap.value!!)
                } else {
                    Toast.makeText(context, "There was an error while downloading the player card", Toast.LENGTH_SHORT).show()
                }


            }catch (e : Exception){
                Log.e("ViewModel", "Error downloading image: ${e.message}")
                Toast.makeText(context, "Error downloading image", Toast.LENGTH_SHORT).show()
            }
        }

    }


}