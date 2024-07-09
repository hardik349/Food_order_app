package com.example.foodorder.data


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

private const val BASE_URL = "https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/"

val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


interface FoodApiService {
    @GET("dev/nosh-assignment")
    suspend fun getFoodItems() : List<FoodItem>
}

object FoodApi{
    val retrofitService : FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}







