package com.example.foodorder.model

import androidx.compose.ui.res.stringResource
import com.example.foodorder.R

object LocalDataProvider {

    val defaultFoodTypeData = getFoodTypeData()[0]

    fun getFoodTypeData() : List<FoodTypeInfo>{
        return listOf(
            FoodTypeInfo(
                id =1,
                typeName = R.string.rice_items,
                imageRes = R.drawable.riceitems
            ),
            FoodTypeInfo(
                id =2,
                typeName = R.string.curry,
                imageRes = R.drawable.curry
            ),
            FoodTypeInfo(
                id =3,
                typeName = R.string.biryani,
                imageRes = R.drawable.biryani
            ),
            FoodTypeInfo(
                id =4,
                typeName = R.string.desserts,
                imageRes = R.drawable.desserts
            ),
        )

    }
}