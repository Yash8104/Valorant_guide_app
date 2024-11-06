package com.example.test_miniproject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.agent_details.Data
import com.example.test_miniproject.network.agents.AgentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: AgentsRepository
) : ViewModel() {

    val agents = mutableStateOf<List<Data>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf(false)
    val error_msg = mutableStateOf("")

    init {
        fetchAgents()
    }

    private fun fetchAgents(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                agents.value = repository.getAgents()

                if(agents.value.isEmpty()){
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