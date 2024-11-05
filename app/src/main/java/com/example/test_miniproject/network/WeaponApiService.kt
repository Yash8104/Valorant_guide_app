package com.example.test_miniproject.network

import com.example.test_miniproject.model.weapon_details.Weapon_data
import com.example.test_miniproject.model.weapon_details.Weapons_data
import retrofit2.http.GET
import retrofit2.http.Path

interface WeaponApiService {

    @GET("v1/weapons")
    suspend fun getWeapons() : Weapons_data

    @GET("v1/weapons/{weaponUuid}")
    suspend fun getWeapon(@Path("weaponUuid") weaponUuid : String) : Weapon_data

}