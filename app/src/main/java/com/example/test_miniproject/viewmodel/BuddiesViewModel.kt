package com.example.test_miniproject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.buddies_detail.Data
import com.example.test_miniproject.network.buddies.BuddiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuddiesViewModel @Inject constructor(
    private val repository: BuddiesRepository
) : ViewModel(){

    val buddies = mutableStateOf<List<Data>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf(false)
    val error_msg = mutableStateOf("")

    init {
        fetchBuddies()
    }

    private fun fetchBuddies(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                buddies.value = repository.getBuddies()

                if(buddies.value.isEmpty()){
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