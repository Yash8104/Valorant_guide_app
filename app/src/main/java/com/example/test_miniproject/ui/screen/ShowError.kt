package com.example.test_miniproject.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test_miniproject.R
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.fontFamily

@Composable
fun ShowError(errorMsg : String){

    var isVisible by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

            Image(
                painter = painterResource(id = R.drawable.alarm),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )


            Text(
                text = "Error in loading the content !!!",
                fontFamily = fontFamily,
                color = BorderColorMera,
                fontSize = 16.sp,
                modifier = Modifier.padding(10.dp)
            )

            Button(onClick = { isVisible = !isVisible }) {
                Text(
                    text = "Show Error Message",
                    fontFamily = fontFamily
                )
            }

        if (isVisible){
            Text(
                text = errorMsg,
                fontFamily = fontFamily,
                color = BorderColorMera,
                )
        }




    }

}