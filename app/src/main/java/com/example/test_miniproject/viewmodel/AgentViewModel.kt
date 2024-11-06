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
class AgentViewModel @Inject constructor(
    private val repository: AgentsRepository
) : ViewModel() {

    val agent = mutableStateOf<Data?>(null)
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf(false)
    val error_msg = mutableStateOf("")

    fun fetchAgent(uuid : String){
        viewModelScope.launch {
            isLoading.value = true
            try {
                agent.value = repository.getAgent(uuid)

                if(agent.value == null){
                    error.value = true
                    error_msg.value = repository.getErrorMessage()

                }else{
                    error.value = false
                }

            }catch (e : Exception){
                e.printStackTrace()
                agent.value = null
                error.value = true
                error_msg.value = e.message.toString()

            }finally {
                isLoading.value = false
            }
        }
    }

}