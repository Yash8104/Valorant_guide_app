package com.example.test_miniproject.ui.screen.ranks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.test_miniproject.R
import com.example.test_miniproject.ui.screen.HomePage
import com.example.test_miniproject.ui.screen.Loading
import com.example.test_miniproject.ui.screen.ShowError
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.TrackColorMera
import com.example.test_miniproject.ui.theme.fontFamily
import com.example.test_miniproject.viewmodel.RanksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RanksScreen(navController: NavController){

    val ranksViewModel: RanksViewModel = hiltViewModel()
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
                    Text(text = "RANKS",
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

        if(!ranksViewModel.isLoading.value){
            
            if (!ranksViewModel.error.value){
                RanksScreenContent(values,ranksViewModel)
            }else{
                ShowError(errorMsg = ranksViewModel.error_msg.value)
            }

        }else{
            Loading()
        }



    }


}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RanksScreenContent(values: PaddingValues, ranksViewModel: RanksViewModel){

    FlowRow(
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMera)
            .padding(values)
            .verticalScroll(rememberScrollState())
    ) {

        val tiers = ranksViewModel.ranks.value?.tiers

        if (tiers != null){

            for (i in tiers){

                if(i.tier >= 3){


                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(150.dp)
                            .width(110.dp)
                            .border(
                                1.dp, BorderColorMera,
                                RoundedCornerShape(12.dp)
                            )
                    ){

                        AsyncImage(
                            model = i.largeIcon,
                            contentDescription = null,
                            modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp),
                        )

                        Text(
                            text = i.tierName,
                            fontFamily = fontFamily,
                            color = BorderColorMera,
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )

                    }



                }

            }

        }

    }

}


