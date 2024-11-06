package com.example.test_miniproject.model.buddies_detail

data class Data(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val isHiddenIfNotOwned: Boolean,
    val levels: List<Level>,
    val themeUuid: String,
    val uuid: String
)