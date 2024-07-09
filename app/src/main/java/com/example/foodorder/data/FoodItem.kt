package com.example.foodorder.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodItem(
    val dishName: String,
    val dishId: String,
    @SerialName(value = "imageUrl")
    val imageUrl: String
)
