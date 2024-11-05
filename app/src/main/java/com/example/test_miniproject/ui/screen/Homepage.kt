package com.example.test_miniproject.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test_miniproject.R
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(navController: NavController){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(BackgroundMera)
        ,
        containerColor = BackgroundMera,


        topBar = {
            CenterAlignedTopAppBar(

                title = {
                    Text(text = "VALORANT GUIDE",
                        fontFamily = fontFamily,
                        color = Color.White
                        )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BackgroundMera,
                    scrolledContainerColor = BackgroundMera
                )


            )
        }
    ) {
        values: PaddingValues ->

        HomepageContent(values, navController)


    }

}

@Composable
fun HomepageContent(values: PaddingValues, navController: NavController){



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(values)
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        ItemCard(text = "AGENTS", image = R.drawable.agents , onClick = {
            navController.navigate(AgentList)
        })
        Spacer(modifier = Modifier.fillMaxWidth())

        ItemCard(text = "WEAPONS", image = R.drawable.weapons , onClick = {
            navController.navigate(WeaponList)
        })
        Spacer(modifier = Modifier.fillMaxWidth())

        ItemCard(text = "RANKS", image = R.drawable.ranks , onClick = {
            navController.navigate(RankList)
        })
        Spacer(modifier = Modifier.fillMaxWidth())

        ItemCard(text = "SPRAYS", image = R.drawable.sprays , onClick = {
            navController.navigate(AgentList)
        })
        Spacer(modifier = Modifier.fillMaxWidth())

        ItemCard(text = "PLAYER \nCARDS", image = R.drawable.player_cards , onClick = {
            navController.navigate(PlayerCardList)
        })
        Spacer(modifier = Modifier.fillMaxWidth())

        ItemCard(text = "MAPS", image = R.drawable.maps , onClick = {
            navController.navigate(AgentList)
        })
        Spacer(modifier = Modifier.fillMaxWidth())

        ItemCard(text = "GUN \nBUDDIES", image = R.drawable.gunbuddies , onClick = {
            navController.navigate(AgentList)
        })
        Spacer(modifier = Modifier.fillMaxWidth())





    }


}

@Composable
fun ItemCard(text: String, image: Int , onClick : ()-> Unit){

    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .size(180.dp)
            .border(width = 1.dp, color = BorderColorMera, shape = RectangleShape)
            .background(color = BackgroundMera)
            .clickable {
                onClick()
            }
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {




        Text(
            text = text,
            fontFamily = fontFamily,
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 10.dp),
            textAlign = TextAlign.Center


        )
        Image(
            painter = painterResource(id = image),
            contentDescription = null
        )

    }



}