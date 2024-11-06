package com.example.test_miniproject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.playercards.Data
import com.example.test_miniproject.network.playercards.PlayerCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerCardsViewModel @Inject constructor(
    private val repository: PlayerCardsRepository
): ViewModel(){


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

}