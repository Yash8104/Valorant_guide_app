package com.example.test_miniproject.ui.screen

import com.example.test_miniproject.model.agent_details.Data
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
data class AgentDetails(
    val uuid : String
)

@Serializable
data class WeaponDetails(
    val uuid : String
)
