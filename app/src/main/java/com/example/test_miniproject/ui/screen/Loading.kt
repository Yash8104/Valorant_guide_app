package com.example.test_miniproject.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.TrackColorMera
import com.example.test_miniproject.ui.theme.fontFamily

@Composable
fun Loading(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMera),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        CircularProgressIndicator(
            color = BorderColorMera,
            strokeWidth = 4.dp,
            trackColor = TrackColorMera,
        )

        Text(
            text = "Loading...",
            fontFamily = fontFamily,
            color = Color.White,
            fontSize = 13.sp
        )

    }
}