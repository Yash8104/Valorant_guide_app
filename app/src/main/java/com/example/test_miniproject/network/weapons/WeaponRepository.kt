package com.example.test_miniproject.network.weapons

import android.util.Log
import com.example.test_miniproject.model.weapon_details.Data
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeaponRepository @Inject constructor(
    private val weaponApiService: WeaponApiService
) {

    suspend fun getWeapons(): List<Data> {
        return try {

            Log.e("test","im in weapon repo")
            val response = weaponApiService.getWeapons()
            Log.e("response",response.status.toString())
            if (response.status == 200){
                response.data
            }else{
                emptyList()
            }

        }catch (e : Exception){
            e.printStackTrace()
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
                null
            }

        }catch (e: Exception){
            e.printStackTrace()
            null
        }

    }

}