package com.example.test_miniproject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_miniproject.model.weapon_details.Data
import com.example.test_miniproject.network.weapons.WeaponRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponViewModel @Inject constructor(
    private val repository: WeaponRepository
) : ViewModel(){

    val weapon = mutableStateOf<Data?>(null)
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf(false)
    val error_msg = mutableStateOf("")

    fun fetchWeapon(uuid : String){
        viewModelScope.launch {
            isLoading.value = true
            try {
                weapon.value = repository.getWeapon(uuid)

                if(weapon.value == null){
                    error.value = true
                    error_msg.value = repository.getErrorMessage()

                }else{
                    error.value = false
                }

            }catch (e : Exception){
                e.printStackTrace()
                weapon.value = null
                error.value = true
                error_msg.value = e.message.toString()

            }finally {
                isLoading.value = false
            }
        }
    }


}