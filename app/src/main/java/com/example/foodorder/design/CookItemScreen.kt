package com.example.foodorder.design

import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material3.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.foodorder.FoodItemViewModel
import com.example.foodorder.R
import com.example.foodorder.Screen
import com.example.foodorder.data.FoodApiService
import com.example.foodorder.data.FoodItem
import com.example.foodorder.data.retrofit
import com.example.foodorder.model.LocalDataProvider
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookItemScreen(
) {
    var search by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp)
            .verticalScroll(scrollState)
    ) {
        TopBarRow()
        Spacer(modifier = Modifier.height(30.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2253A5)
            ),
            modifier = Modifier
                //.fillMaxWidth()
                .padding(end = 12.dp)
                .clip(RoundedCornerShape(30.dp))
                .size(height = 52.dp, width = 374.dp)

        ) {

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(top = 2.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(30.dp))
                    .size(height = 48.dp, width = 358.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {

                    Image(
                        painter = painterResource(R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier
                            .size(38.dp)
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp, top = 6.dp)
                    )
                    TextField(
                        value = search,
                        onValueChange = {
                            search = it
                        },
                        label = {
                            if (search.isEmpty()) {
                                Text(
                                    text = "Search your dish or ingredient",
                                    fontSize = 18.sp,
                                    color = Color.LightGray,
                                    style = MaterialTheme.typography.displayMedium,
                                    modifier = Modifier
                                        .padding(top = 4.dp)

                                )
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            cursorColor = Color(0xFF2253A5)
                        ),
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "What's on your mind?",
            fontSize = 25.sp,
            color = Color(0xFF2253A5),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodTypeDataItemList(
            foodItemList = LocalDataProvider.getFoodTypeData()
        )
        Spacer(modifier = Modifier.height(23.dp))
        Text(
            text = "Recommendations",
            fontSize = 25.sp,
            color = Color(0xFF2253A5),
            style = MaterialTheme.typography.displayMedium
        )

        val viewModel: FoodItemViewModel = viewModel()
        FoodItemScreen(foodItemUiState = viewModel.foodItemUiState)

        Spacer(modifier = Modifier.height(35.dp))
        Image(
            painter = painterResource(R.drawable.dishes) ,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 80.dp, width = 200.dp)
                .padding(start = 15.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally)

        )
        Spacer(modifier = Modifier.height(15.dp))
        Image(
            painter = painterResource(R.drawable.desc) ,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 80.dp, width = 200.dp)
                .padding(start = 15.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally)

        )

    }

}


@Composable
fun CookItemCard(
    foodItem: FoodItem
) {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))

    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(10.dp)
                .size(height = 280.dp, width = 200.dp)
                .clickable {
                    showDialog = true
                }
            ) {

            Surface(
                modifier = Modifier
                    .size(height = 170.dp, width = 180.dp)
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(foodItem.imageUrl),
                    contentDescription = "food_image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = foodItem.dishName,
                    fontSize = 22.sp,
                    color = Color(0xFF2253A5),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier

                )
                Spacer(modifier = Modifier.height(17.dp))
                Row() {
                    Image(
                        painter = painterResource(R.drawable.cook),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "30 min",
                        fontSize = 13.sp,
                        style = MaterialTheme.typography.displayMedium,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Medium prep.",
                        fontSize = 13.sp,
                        style = MaterialTheme.typography.displayMedium,
                    )
                }
            }
        }
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFFDD4F22),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 50.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(18.dp)

                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "4.2",
                    color = Color.White,
                    fontSize = 12.sp
                )

            }

        }
    }

    if(showDialog){
       TimePickerDialog(
           onDismissButtonClicked = { showDialog = false}
       )
    }
}

@Composable
fun BottomNavigationFunction(
    navController: NavHostController
) {
    val items = listOf(
        Screen.Cook,
        Screen.Favourites,
        Screen.Manual,
        Screen.Device,
        Screen.Preferences,
        Screen.Setting
    )
    var selectedIndex by remember { mutableStateOf(0) }

    AnimatedNavigationBar(
        selectedIndex = selectedIndex,
        modifier = Modifier
            .height(60.dp),
        cornerRadius = shapeCornerRadius(cornerRadius = 50.dp),
        ballAnimation = Straight(tween(300)),
        indentAnimation = Height(tween(600)),
        ballColor = Color(0xFFFF6247),
        barColor = Color(0xFFF5EFED)


    ) {
        items.forEachIndexed { index, screen ->
            IconButton(
                onClick = {
                    selectedIndex = index
                    navController.navigate(items[index].name)
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(screen.iconResId),
                        contentDescription = null,
                        tint = if (selectedIndex == index) Color.Black else Color.Gray,
                        modifier = Modifier
                            .size(35.dp)
                            .padding(
                                if (selectedIndex == index) 5.dp else 2.dp
                            )
                    )
                    /* if (selectedIndex == index) {
                         Text(
                             text = screen.name
                         )
                     }*/
                }

            }
        }

    }
}

@Composable
fun TopBarRow(

) {
    val animatedAlpha by animateFloatAsState(
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 20.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(30.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1F2D3F)
            ),
            shape = RoundedCornerShape(60.dp),
            modifier = Modifier

        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        //.background(Color.LightGray.copy(alpha = animatedAlpha))
                        .padding(2.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.biryani),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 8.dp, bottom = 8.dp, end = 14.dp)

                ) {
                    Text(
                        text = "Chicken Biryani..",
                        fontSize = 16.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "Scheduled 6:30 AM",
                        fontSize = 11.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.displayMedium
                    )
                }

            }

        }
        Spacer(modifier = Modifier.width(25.dp))
        Image(
            painter = painterResource(R.drawable.bell),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painter = painterResource(R.drawable.power),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun CookItemScreenPreview() {
    CookItemScreen()
}

@Preview(showBackground = true)
@Composable
fun CookItemCardPreview() {
    CookItemCard(
        foodItem = FoodItem(
            dishName = "",
            dishId = "",
            imageUrl = ""
        )
    )
}