package com.example.test_miniproject.ui.screen.buddies

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.test_miniproject.R
import com.example.test_miniproject.ui.screen.HomePage
import com.example.test_miniproject.ui.screen.Loading
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.fontFamily
import com.example.test_miniproject.viewmodel.BuddiesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuddiesScreen(navController: NavController){

    val buddiesViewModel : BuddiesViewModel = hiltViewModel()
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
                    Text(text = "BUDDIES",
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

        if(!buddiesViewModel.isLoading.value){
            BuddiesScreenContent(values,buddiesViewModel, navController)
        }else{
            Loading()
        }



    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BuddiesScreenContent(values: PaddingValues, buddiesViewModel: BuddiesViewModel, navController : NavController){


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMera)
            .padding(values),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(buddiesViewModel.buddies.value.chunked(2)){

                rowItems ->

            Row {

                rowItems.forEach{
                        i ->

                        Column(

                        ) {
                            AsyncImage(
                                model = i.displayIcon,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                                    .width(170.dp)
                                    .height(200.dp)
                                    .border(1.dp, BorderColorMera, RectangleShape),
//                            contentScale = ContentScale.FillWidth,
                                placeholder = painterResource(id = R.drawable.spray_placeholder),
                                error = painterResource(id = R.drawable.spray_error)

                            )

                            Text(
                                text = i.displayName,
                                fontFamily = fontFamily,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .background(BorderColorMera)
                                    .width(170.dp)
                                ,
                                textAlign = TextAlign.Center,
                                minLines = 2
                            )

                        }



                }

            }

        }
    }


}