package com.example.test_miniproject.model.buddies_detail

data class Level(
    val assetPath: String,
    val charmLevel: Int,
    val displayIcon: String,
    val displayName: String,
    val hideIfNotOwned: Boolean,
    val uuid: String
)