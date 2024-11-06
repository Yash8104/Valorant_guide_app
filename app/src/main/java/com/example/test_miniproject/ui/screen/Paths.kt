package com.example.test_miniproject.ui.screen

import kotlinx.serialization.Serializable


@Serializable
object HomePage

@Serializable
object AgentList

@Serializable
object WeaponList

@Serializable
object RankList

@Serializable
object PlayerCardList

@Serializable
object SprayList

@Serializable
object BuddiesList

@Serializable
data class AgentDetails(
    val uuid : String
)

@Serializable
data class WeaponDetails(
    val uuid : String
)
