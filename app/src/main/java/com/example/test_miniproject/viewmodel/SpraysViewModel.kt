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

    init {
        fetchSprays()
    }

    private fun fetchSprays(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                sprays.value = sprayRepository.getSprays()

            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                isLoading.value = false
            }
        }
    }

}