package com.example.test_miniproject.ui.screen.weapons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.test_miniproject.R
import com.example.test_miniproject.ui.screen.AgentDetails
import com.example.test_miniproject.ui.screen.HomePage
import com.example.test_miniproject.ui.screen.Loading
import com.example.test_miniproject.ui.screen.WeaponDetails
import com.example.test_miniproject.ui.screen.agents.AgentsCard
import com.example.test_miniproject.ui.screen.agents.AgentsList
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.fontFamily
import com.example.test_miniproject.viewmodel.WeaponsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponsScreen(navController: NavController){

    val weaponsViewModel: WeaponsViewModel = hiltViewModel()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(BackgroundMera),
        containerColor = BackgroundMera,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "WEAPONS",
                        fontFamily = fontFamily,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(HomePage)
                    }) {
//                        hsla(355, 100%, 64%, 1)
                        Icon(painter = painterResource(id = R.drawable.vector_2), contentDescription = null, tint = BorderColorMera)

                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BackgroundMera,
                    scrolledContainerColor = BackgroundMera
                )
            )
        }
    ){

        values: PaddingValues ->

        if(!weaponsViewModel.isLoading.value){

            WeaponsScreenContent(values, weaponsViewModel, navController)

        }else{
            Loading()
        }


    }




}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WeaponsScreenContent(values: PaddingValues, weaponsViewModel: WeaponsViewModel, navController: NavController){


    FlowRow(
        maxItemsInEachRow = 1,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMera)
            .padding(values)
            .verticalScroll(rememberScrollState())
    ) {

        for (i in weaponsViewModel.weapons.value){



            WeaponsCard(
                text = i.displayName,
                image = i.displayIcon,
                onClick = {
                    navController.navigate(WeaponDetails(i.uuid))
                }
            )

        }



    }



}

@Composable
fun WeaponsCard(text: String, image: String, onClick: ()-> Unit){

    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .border(2.dp, color = BorderColorMera, shape = RectangleShape)
            .clickable {
                onClick()
            }

        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = text,
            fontFamily = fontFamily,
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 16.dp)
        )


        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .padding(20.dp)
        )



    }

}