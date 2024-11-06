package com.example.test_miniproject.ui.screen.sprays

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.test_miniproject.ui.theme.fontFamily
import com.example.test_miniproject.viewmodel.SpraysViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpraysScreen(navController: NavController){

    val spraysViewModel : SpraysViewModel = hiltViewModel()
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
                    Text(text = "SPRAYS",
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

        if(!spraysViewModel.isLoading.value){
            
            if(!spraysViewModel.error.value){
                SpraysScreenContent(values,spraysViewModel , navController)
            }else{
                ShowError(errorMsg = spraysViewModel.error_msg.value)
            }
            
        }else{
            Loading()
        }



    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SpraysScreenContent(values: PaddingValues, spraysViewModel: SpraysViewModel, navController : NavController){


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMera)
            .padding(values),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(spraysViewModel.sprays.value.chunked(2)){

                rowItems ->

            Row {

                rowItems.forEach{
                        i ->
                        if (i.animationGif.isNullOrBlank()){
                            AsyncImage(
                                model = i.fullTransparentIcon,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .width(170.dp)
                                    .border(1.dp, BorderColorMera, RoundedCornerShape(8.dp))
                                    .clip(RoundedCornerShape(8.dp))
                                ,
                                contentScale = ContentScale.FillWidth,
                                placeholder = painterResource(id = R.drawable.spray_placeholder),
                                error = painterResource(id = R.drawable.spray_error)
                            )
                        }else{
                            AsyncImage(
                                model = i.animationGif,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .width(170.dp)
                                    .border(1.dp, BorderColorMera, RoundedCornerShape(8.dp))
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.FillWidth,
                                placeholder = painterResource(id = R.drawable.spray_placeholder),
                                error = painterResource(id = R.drawable.spray_error)

                            )
                        }


                }

            }

        }
    }


}