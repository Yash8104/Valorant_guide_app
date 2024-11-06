package com.example.test_miniproject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.rank_details.Data
import com.example.test_miniproject.network.ranks.RankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RanksViewModel @Inject constructor(
    private val repository: RankRepository
) : ViewModel(){


    val ranks = mutableStateOf<Data?>(null)
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf(false)
    val error_msg = mutableStateOf("")

    init {
        fetchRanks()
    }
    private fun fetchRanks(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                ranks.value = repository.getRanks()

                if(ranks.value == null){
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