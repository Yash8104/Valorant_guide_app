package com.example.test_miniproject.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.test_miniproject.ui.screen.agents.AgentScreen
import com.example.test_miniproject.ui.screen.agents.AgentsListWrapper
import com.example.test_miniproject.ui.screen.buddies.BuddiesScreen
import com.example.test_miniproject.ui.screen.playercards.PlayerCardsScreen
import com.example.test_miniproject.ui.screen.ranks.RanksScreen
import com.example.test_miniproject.ui.screen.sprays.SpraysScreen
import com.example.test_miniproject.ui.screen.weapons.WeaponScreen
import com.example.test_miniproject.ui.screen.weapons.WeaponsScreen
import com.example.test_miniproject.viewmodel.AgentsViewModel
import kotlinx.serialization.Serializable


@Composable
fun Navigation(){

    
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = HomePage) {

        composable<HomePage> {
            Homepage(navController)
        }


        composable<AgentList> { 
            AgentsListWrapper(navController = navController)
        }


        composable<WeaponList> {

            WeaponsScreen(navController = navController)

        }

        composable<RankList> {
            RanksScreen(navController = navController)
        }

        composable<PlayerCardList> {
            PlayerCardsScreen(navController = navController)
        }

        composable<SprayList> {
            SpraysScreen(navController = navController)
        }

        composable<BuddiesList> {
            BuddiesScreen(navController = navController)
        }


        composable<AgentDetails> {

            navBackStackEntry ->

            val agentDetails_ : AgentDetails = navBackStackEntry.toRoute()
            AgentScreen(agentUuid = agentDetails_.uuid, navController = navController)

        }

        composable<WeaponDetails> { 
            navBackStackEntry -> 
            val weaponDetails_ : WeaponDetails = navBackStackEntry.toRoute()
            WeaponScreen(weaponUuid = weaponDetails_.uuid, navController = navController)
        }







    }
    

}