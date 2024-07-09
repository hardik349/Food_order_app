package com.example.foodorder.model

import androidx.annotation.StringRes

data class FoodTypeInfo(
    val id: Int,
    @StringRes val typeName : Int,
    val imageRes : Int
)
