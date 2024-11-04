package com.example.test_miniproject.ui.screen.agents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import coil3.request.crossfade
import com.example.test_miniproject.R
import com.example.test_miniproject.ui.screen.HomePage
import com.example.test_miniproject.ui.screen.HomepageContent
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.fontFamily
import com.example.test_miniproject.viewmodel.AgentsViewModel




@Composable
fun AgentsListWrapper(agentsViewModel: AgentsViewModel, navController: NavController){

    if(!agentsViewModel.isLoading.value){



        AgentsList(agentsViewModel = agentsViewModel, navController)

    }else{

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CircularProgressIndicator(
                color = Color.Cyan,
                strokeWidth = 4.dp,
                trackColor = Color.hsl(180F,1F,0.94F),

                )

        }

    }

}




@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AgentsList(agentsViewModel: AgentsViewModel , navController: NavController){

    val imageloader = ImageLoader.Builder(LocalContext.current).crossfade(true).build()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(BackgroundMera)
        ,
        containerColor = BackgroundMera,


        topBar = {
            CenterAlignedTopAppBar(

                title = {
                    Text(text = "AGENTS",
                        fontFamily = fontFamily,
                        color = Color.White
                    )
                },

                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(HomePage)
                    }) {
//                        hsla(355, 100%, 64%, 1)
                        Icon(painter = painterResource(id = R.drawable.vector_2), contentDescription = null, tint = Color.hsl(355F,1F,0.64F))
                        
                    }
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

        FlowRow(
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundMera)
                .padding(values)
                .verticalScroll(rememberScrollState())
        ) {

            for (i in agentsViewModel.agents.value){

                AgentsCard(text = i.displayName, image = i.fullPortrait, imageloader = imageloader)

            }



        }


    }

}


@Composable
fun AgentsCard(text: String, image: String, imageloader: ImageLoader){


    Column(
        modifier = Modifier
            .padding(15.dp)
            .width(160.dp)
            .border(2.dp, color = BorderColorMera, shape = RectangleShape)

        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = text,
            fontFamily = fontFamily,
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Image(
            painter = rememberAsyncImagePainter(
                model = image,
                imageLoader = imageloader
            ) ,
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(250.dp),
            contentScale = ContentScale.Crop

        )



    }


}