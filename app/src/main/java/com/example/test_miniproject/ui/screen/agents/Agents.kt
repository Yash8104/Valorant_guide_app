package com.example.test_miniproject.ui.screen.agents

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.crossfade
import com.example.test_miniproject.R
import com.example.test_miniproject.ui.screen.AgentDetails
import com.example.test_miniproject.ui.screen.HomePage
import com.example.test_miniproject.ui.screen.HomepageContent
import com.example.test_miniproject.ui.screen.Loading
import com.example.test_miniproject.ui.screen.ShowError
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.TrackColorMera
import com.example.test_miniproject.ui.theme.fontFamily
import com.example.test_miniproject.viewmodel.AgentsViewModel




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentsListWrapper(navController: NavController){

    val agentsViewModel: AgentsViewModel = hiltViewModel()


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

        if(!agentsViewModel.isLoading.value){

            if (!agentsViewModel.error.value){
                AgentsList(values,agentsViewModel = agentsViewModel, navController)
            }else{

//                Error message
                ShowError(agentsViewModel.error_msg.value)

            }

        }else{
            Loading()
        }



    }



}




@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AgentsList(values: PaddingValues, agentsViewModel: AgentsViewModel , navController: NavController){

//    val imageloader = ImageLoader.Builder(LocalContext.current).crossfade(true).build()



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

                AgentsCard(
                    text = i.displayName,
                    image = i.fullPortrait,
                    onClick = {
                        navController.navigate(AgentDetails(i.uuid))
                    }
                )

            }



        }


}




@Composable
fun AgentsCard(text: String, image: String, onClick: ()-> Unit){


    Column(
        modifier = Modifier
            .padding(15.dp)
            .width(160.dp)
            .border(1.dp, color = BorderColorMera, shape = RoundedCornerShape(10.dp))
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
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 16.dp)
        )


        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(250.dp),
            contentScale = ContentScale.Crop
        )



    }


}