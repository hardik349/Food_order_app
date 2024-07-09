package com.example.foodorder.design

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodorder.FoodItemUiState
import com.example.foodorder.data.FoodItem


@Composable
fun FoodItemScreen(
    foodItemUiState: FoodItemUiState
) {
    when (foodItemUiState) {
        is FoodItemUiState.Loading -> LoadingScreen()
        is FoodItemUiState.Success -> ResultScreen(foodItem = foodItemUiState.foodItems)
        is FoodItemUiState.Error -> ErrorScreen()
    }

}

@Composable
fun ResultScreen(
    foodItem: List<FoodItem>
) {
    LazyRow {
        items(foodItem) { foodItem ->
            CookItemCard(foodItem = foodItem)

        }

    }
}

@Composable
fun LoadingScreen(

) {
    Text(
        text = "Loading..",
        fontSize = 20.sp,
        style = MaterialTheme.typography.displayMedium,
        color = Color(0xFFDD4F22),
        modifier = Modifier.padding(15.dp)
    )
}

@Composable
fun ErrorScreen() {
    Text(
        text = "error",
        fontSize = 30.sp
    )

}

