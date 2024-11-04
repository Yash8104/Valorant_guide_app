package com.example.test_miniproject.ui.screen.agents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.test_miniproject.viewmodel.AgentViewModel

@Composable
fun AgentScreen(agentUuid: String, navController: NavController){

    val viewModel: AgentViewModel = hiltViewModel()

    LaunchedEffect(agentUuid) {
        viewModel.fetchAgent(agentUuid)
    }

    val agent = viewModel.agent.value
    val isLoading = viewModel.isLoading.value


        if (!isLoading){

            agent?.let {

                Column {

                    Text(text = it.displayName)
                    AsyncImage(model = it.displayIcon, contentDescription = null)

                }
            } ?: run { 
                Text(text = "nai hua load yaar")
            }



        }
        else{

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }

        }

    }



