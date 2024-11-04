package com.example.test_miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.test_miniproject.ui.screen.Navigation
import com.example.test_miniproject.ui.theme.BackgroundMera

import com.example.test_miniproject.viewmodel.AgentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Surface (
                color = BackgroundMera
            ){
                Navigation()

            }

        }

    }
}

