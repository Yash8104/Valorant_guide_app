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


    fun fetchAgent(uuid : String){
        viewModelScope.launch {
            isLoading.value = true
            try {
                agent.value = repository.getAgent(uuid)
            }catch (e : Exception){
                e.printStackTrace()
                agent.value = null
            }finally {
                isLoading.value = false
            }
        }
    }

}