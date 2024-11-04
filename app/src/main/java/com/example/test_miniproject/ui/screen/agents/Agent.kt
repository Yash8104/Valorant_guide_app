package com.example.test_miniproject.ui.screen.agents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.test_miniproject.R
import com.example.test_miniproject.model.agent_details.Data
import com.example.test_miniproject.ui.screen.AgentList
import com.example.test_miniproject.ui.screen.HomePage
import com.example.test_miniproject.ui.screen.Loading
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.fontFamily
import com.example.test_miniproject.viewmodel.AgentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentScreen(agentUuid: String, navController: NavController){

    val viewModel: AgentViewModel = hiltViewModel()

    LaunchedEffect(agentUuid) {
        viewModel.fetchAgent(agentUuid)
    }

    val agent = viewModel.agent.value
    val isLoading = viewModel.isLoading.value
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
                    Text(text = "AGENTS DETAILS",
                        fontFamily = fontFamily,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(AgentList)
                    }) {
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


        if (!isLoading){

            agent?.let {
//                logic
                AgentScreenContent(values,it)

            } ?: run {
                Text(text = "Something went wrong!!", color = Color.Red, fontFamily = fontFamily, fontSize = 20.sp, modifier = Modifier.padding(values))
            }
        }
        else{
            Loading()
        }

    }

}

@Composable
fun AgentScreenContent(values: PaddingValues,agent: Data){


    Column(
        modifier = Modifier
            .padding(top = values.calculateTopPadding(), start = 50.dp, end = 50.dp, bottom = 50.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = agent.fullPortrait,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(bottom = 20.dp)

            ,
            contentScale = ContentScale.Crop,
            clipToBounds = true

        )


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "NAME:",
                fontFamily = fontFamily,
                color = BorderColorMera
            )

            Text(
                text = agent.displayName,
                fontFamily = fontFamily,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )


        }
        HorizontalDivider(color = Color.White, modifier = Modifier.fillMaxWidth())

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                text = "TYPE:",
                fontFamily = fontFamily,
                color = BorderColorMera
            )

            Row (
                verticalAlignment = Alignment.CenterVertically,

            ){

                AsyncImage(
                    model = agent.role.displayIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = agent.role.displayName,
                    fontFamily = fontFamily,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )

            }
        }

        HorizontalDivider(color = Color.White, modifier = Modifier.fillMaxWidth())

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            val text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = BorderColorMera)){
                    append("DESCRIPTION: ")
                }
                withStyle(style = SpanStyle(color = Color.White)){
                    append(agent.description)
                }

            }

            Text(
                text = text,
                fontFamily = fontFamily,
                modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                textAlign = TextAlign.Justify
            )




        }

        HorizontalDivider(color = Color.White, modifier = Modifier.fillMaxWidth())

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "ABILITIES",
                fontFamily = fontFamily,
                color = BorderColorMera,
                modifier = Modifier.padding(top=10.dp),
                textAlign = TextAlign.Start
            )
        }


//        card
        for (i in agent.abilities){

            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .border(color = BorderColorMera, width = 2.dp, shape = RectangleShape)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = i.displayName,
                        fontFamily = fontFamily,
                        color = BorderColorMera
                    )

                    AsyncImage(
                        model = i.displayIcon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Fit
                    )

                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    val text_desc = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = BorderColorMera)){
                            append("DESCRIPTION: ")
                        }
                        withStyle(style = SpanStyle(color = Color.White)){
                            append(i.description)
                        }

                    }



                    Text(
                        text = text_desc,
                        fontFamily = fontFamily,
                        modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                        textAlign = TextAlign.Justify
                    )


                }



            }


        }


    }


}



