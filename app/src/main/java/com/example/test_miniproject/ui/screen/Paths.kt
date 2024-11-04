package com.example.test_miniproject.ui.screen

import com.example.test_miniproject.model.agent_details.Data
import kotlinx.serialization.Serializable

@Serializable
object AgentList

@Serializable
object HomePage

@Serializable
data class AgentDetails(
    val uuid : String
)