package com.example.test_miniproject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.spray_details.Data
import com.example.test_miniproject.network.sprays.SprayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpraysViewModel @Inject constructor(
    private val sprayRepository: SprayRepository
) : ViewModel(){

    val sprays = mutableStateOf<List<Data>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf(false)
    val error_msg = mutableStateOf("")


    init {
        fetchSprays()
    }

    private fun fetchSprays(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                sprays.value = sprayRepository.getSprays()

                if(sprays.value.isEmpty()){
                    error.value = true
                    error_msg.value = sprayRepository.getErrorMessage()

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