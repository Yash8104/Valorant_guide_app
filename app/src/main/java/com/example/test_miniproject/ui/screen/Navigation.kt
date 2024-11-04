package com.example.test_miniproject.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test_miniproject.ui.screen.agents.AgentsListWrapper
import com.example.test_miniproject.viewmodel.AgentsViewModel
import kotlinx.serialization.Serializable


@Composable
fun Navigation(agentsViewModel: AgentsViewModel){

    
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = HomePage) {

        composable<HomePage> {
            Homepage(navController)
        }


        composable<AgentList> { 
            AgentsListWrapper(agentsViewModel = agentsViewModel, navController = navController)
        }



    }
    

}