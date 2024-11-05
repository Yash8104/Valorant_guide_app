package com.example.test_miniproject.ui.screen.weapons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.test_miniproject.model.weapon_details.Data
import com.example.test_miniproject.ui.screen.AgentList
import com.example.test_miniproject.ui.screen.Loading
import com.example.test_miniproject.ui.screen.WeaponList
import com.example.test_miniproject.ui.screen.agents.AgentScreenContent
import com.example.test_miniproject.ui.theme.BackgroundMera
import com.example.test_miniproject.ui.theme.BorderColorMera
import com.example.test_miniproject.ui.theme.fontFamily
import com.example.test_miniproject.viewmodel.WeaponViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponScreen(weaponUuid: String, navController: NavController){

    val viewModel: WeaponViewModel = hiltViewModel()
    
    LaunchedEffect(weaponUuid) {
        viewModel.fetchWeapon(weaponUuid)
    }

    val weapon = viewModel.weapon.value
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
                    Text(text = "WEAPON DETAILS",
                        fontFamily = fontFamily,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(WeaponList)
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

            weapon?.let {
//                logic
                WeaponScreenContent(values,it)

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
fun WeaponScreenContent(values: PaddingValues, weapon: Data){


    Column(
        modifier = Modifier
            .padding(top = values.calculateTopPadding(), start = 50.dp, end = 50.dp, bottom = 50.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = weapon.displayIcon,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(vertical = 20.dp)
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
                text = weapon.displayName,
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

            Text(
                text = weapon.shopData.category,
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
                text = "CREDS:",
                fontFamily = fontFamily,
                color = BorderColorMera
            )

            Text(
                text = weapon.shopData.cost.toString(),
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
                text = "MAGAZINE:",
                fontFamily = fontFamily,
                color = BorderColorMera
            )

            Text(
                text = weapon.weaponStats.magazineSize.toString(),
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
                text = "WALL PENETRATION:",
                fontFamily = fontFamily,
                color = BorderColorMera
            )

            Text(
                text = weapon.weaponStats.wallPenetration.replace("EWallPenetrationDisplayType::","",true),
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
                text = "FIRE RATE:",
                fontFamily = fontFamily,
                color = BorderColorMera
            )

            Text(
                text = weapon.weaponStats.fireRate.toString() + " /SEC",
                fontFamily = fontFamily,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )


        }

        HorizontalDivider(color = Color.White, modifier = Modifier.fillMaxWidth())

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "DAMAGE",
                fontFamily = fontFamily,
                color = BorderColorMera,
                modifier = Modifier.padding(top = 10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                ,

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "RANGE",
                    fontFamily = fontFamily,
                    color = BorderColorMera,
                    modifier = Modifier.width(60.dp),
                    textAlign = TextAlign.Center

                )

                Text(
                    text = "BODY",
                    fontFamily = fontFamily,
                    color = BorderColorMera,
                    modifier = Modifier.width(50.dp),
                    textAlign = TextAlign.Center


                )

                Text(
                    text = "HEAD",
                    fontFamily = fontFamily,
                    color = BorderColorMera,
                    modifier = Modifier.width(50.dp),
                    textAlign = TextAlign.Center


                )

                Text(
                    text = "LEG",
                    fontFamily = fontFamily,
                    color = BorderColorMera,
                    modifier = Modifier.width(50.dp),
                    textAlign = TextAlign.Center


                )

            }




                for (i in weapon.weaponStats.damageRanges) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        val text = i.rangeStartMeters.toString() + "-" + i.rangeEndMeters

                        Text(
                            text = text,
                            fontFamily = fontFamily,
                            color = Color.White,
                            modifier = Modifier.width(60.dp),
                            textAlign = TextAlign.Center

                        )

                        Text(
                            text = i.bodyDamage.toInt().toString(),
                            fontFamily = fontFamily,
                            color = Color.White,
                            modifier = Modifier.width(50.dp),
                            textAlign = TextAlign.Center


                        )

                        Text(
                            text = i.headDamage.toInt().toString(),
                            fontFamily = fontFamily,
                            color = Color.White,
                            modifier = Modifier.width(50.dp),
                            textAlign = TextAlign.Center


                        )

                        Text(
                            text = i.legDamage.toInt().toString(),
                            fontFamily = fontFamily,
                            color = Color.White,
                            modifier = Modifier.width(50.dp),
                            textAlign = TextAlign.Center

                        )

                    }

                }





        }

        HorizontalDivider(color = Color.White, modifier = Modifier.fillMaxWidth())


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {

            Text(
                text = "SKINS:",
                fontFamily = fontFamily,
                color = BorderColorMera
            )



        }

        LazyRow (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){


            items(weapon.skins){

                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .border(1.dp, BorderColorMera, RectangleShape)

                ) {

                    Text(
                        text = it.displayName,
                        fontFamily = fontFamily,
                        color = BorderColorMera,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )

                    AsyncImage(
                        model = it.displayIcon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(40.dp),
                        clipToBounds = true
                    )
                }

            }


        }

        Text(
            text = "<------->",
            fontFamily = fontFamily,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

    }


}