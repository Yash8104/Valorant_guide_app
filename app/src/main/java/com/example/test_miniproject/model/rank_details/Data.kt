package com.example.test_miniproject.model.rank_details

data class Data(
    val assetObjectName: String,
    val assetPath: String,
    val tiers: List<Tier>,
    val uuid: String
)