package com.example.test_miniproject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.rank_details.Data
import com.example.test_miniproject.network.RankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RanksViewModel @Inject constructor(
    private val repository: RankRepository
) : ViewModel(){


    val ranks = mutableStateOf<Data?>(null)
    val isLoading = mutableStateOf(false)

    init {
        fetchRanks()
    }
    private fun fetchRanks(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                ranks.value = repository.getRanks()
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                isLoading.value = false
            }
        }
    }

}