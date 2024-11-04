package com.example.test_miniproject.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.agent_details.Data
import com.example.test_miniproject.network.AgentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: AgentsRepository
) : ViewModel() {

    val agents = mutableStateOf<List<Data>>(emptyList())
    val isLoading = mutableStateOf(false)

    init {
        fetchAgents()
    }

    private fun fetchAgents(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                agents.value = repository.getAgents()

            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                isLoading.value = false
            }
        }
    }

}