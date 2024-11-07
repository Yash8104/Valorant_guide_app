package com.example.test_miniproject.ui.screen.playercards

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.test_miniproject.R
import com.example.test_miniproject.ui.screen.AgentDetails
import com.example.test_miniproject.ui.screen.HomePage
import com.example.test_miniproject.ui.screen.Loading
import com.example.test_miniproject.ui.screen.ShowError
import com.example.test_miniproject.ui.screen.agents.AgentsCard
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.fontFamily
import com.example.test_miniproject.viewmodel.PlayerCardsViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerCardsScreen(navController: NavController){

    val playerCardsViewModel: PlayerCardsViewModel = hiltViewModel()
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
                    Text(text = "PLAYER CARDS",
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

        if(!playerCardsViewModel.isLoading.value){
            
            if(!playerCardsViewModel.error.value){
                PlayerCardsScreenContent(values,playerCardsViewModel , navController)
            }else{
                ShowError(errorMsg = playerCardsViewModel.error_msg.value)
            }
            
        }else{
            Loading()
        }



    }


}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PlayerCardsScreenContent(values: PaddingValues, playerCardsViewModel: PlayerCardsViewModel , navController : NavController){

    val context = LocalContext.current

//    var permission_state by remember {
//        mutableStateOf(true)
//    }

//    val permissionState = rememberPermissionState(permission = Manifest.permission.WRITE_EXTERNAL_STORAGE)

//    val requestPermissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
//            isGranted ->
//        if(isGranted){
//            permission_state = true
//            Toast.makeText(context,"Granted permission!",Toast.LENGTH_SHORT).show()
//
//        }else{
//            Toast.makeText(context,"Denied permission!",Toast.LENGTH_SHORT).show()
//
//        }
//    }

//    LaunchedEffect (permissionState){
//        if(!permissionState.status.isGranted && permissionState.status.shouldShowRationale){
////            NOTHING FOR NOW
//        }else{
//            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            Log.e("test","did this work??")
//        }
//    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMera)
            .padding(values),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(playerCardsViewModel.cards.value.chunked(2)){

            rowItems ->

            Row (){

                rowItems.forEach{
                    i ->
                    AsyncImage(
                        model = i.largeArt,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .width(170.dp)
                            .border(2.dp, BorderColorMera, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
//                                playerCardsViewModel.downloadImage(context,i.largeArt)

                                playerCardsViewModel.downloadImage(context,i.largeArt)

                            }
                        ,
                        contentScale = ContentScale.FillWidth,
                        placeholder = painterResource(id = R.drawable.placeholder_image_color)
                    )
                }

            }


        }

    }


}