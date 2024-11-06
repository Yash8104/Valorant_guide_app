package com.example.test_miniproject.network.weapons

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.test_miniproject.model.weapon_details.Data
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeaponRepository @Inject constructor(
    private val weaponApiService: WeaponApiService
) {
    var error_message = mutableStateOf("")

    fun getErrorMessage(): String{
        return error_message.value
    }

    suspend fun getWeapons(): List<Data> {
        return try {

            Log.e("test","im in weapon repo")
            val response = weaponApiService.getWeapons()
            Log.e("response",response.status.toString())
            if (response.status == 200){
                response.data
            }else{
                error_message.value = "Response code is not 200."
                emptyList()
            }

        }catch (e : Exception){
            e.printStackTrace()
            error_message.value = e.message.toString()
            emptyList()
        }
    }

    suspend fun getWeapon(uuid : String): Data?{

        return try {
            Log.e("repo","getWeapon() function block")
            val response = weaponApiService.getWeapon(uuid)
            Log.e("response",response.status.toString())
            if (response.status == 200){
                response.data
            }else{
                error_message.value = "Response code is not 200."
                null
            }

        }catch (e: Exception){
            e.printStackTrace()
            error_message.value = e.message.toString()
            null
        }

    }

}