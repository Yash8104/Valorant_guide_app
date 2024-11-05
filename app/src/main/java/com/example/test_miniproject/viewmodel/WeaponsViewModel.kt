package com.example.test_miniproject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.weapon_details.Data
import com.example.test_miniproject.network.WeaponRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(
    private val repository: WeaponRepository
): ViewModel(){

    val weapons = mutableStateOf<List<Data>>(emptyList())
    val isLoading = mutableStateOf(false)

    init {
        fetchWeapons()
    }

    private fun fetchWeapons(){
        viewModelScope.launch {
            isLoading.value = true
            try{
                weapons.value = repository.getWeapons()
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                isLoading.value = false
            }
        }
    }

}