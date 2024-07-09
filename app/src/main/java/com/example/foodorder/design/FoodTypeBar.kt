package com.example.foodorder.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodorder.model.FoodTypeInfo
import com.example.foodorder.model.LocalDataProvider

@Composable
fun FoodTypeDataItem(
    foodTypeInfo: FoodTypeInfo
) {
    Box(
        modifier = Modifier
            .padding(start = 5.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp, // Base elevation

            ),
            shape = RoundedCornerShape(60.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier

        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter= painterResource(foodTypeInfo.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Text(
                    text = stringResource(foodTypeInfo.typeName),
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .padding(start = 9.dp, end = 9.dp)

                )

            }

        }

    }

}

@Composable
fun FoodTypeDataItemList(
    foodItemList : List<FoodTypeInfo>
){
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(foodItemList, key = {foodItem -> foodItem.id}){foodItem ->
            FoodTypeDataItem(
                foodTypeInfo = foodItem
            )


        }


    }

}

@Preview
@Composable
fun FoodItemPreview(){
    FoodTypeDataItem(foodTypeInfo = LocalDataProvider.defaultFoodTypeData)
}