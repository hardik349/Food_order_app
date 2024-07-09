package com.example.foodorder

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodorder.data.FoodApi
import com.example.foodorder.data.FoodItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


sealed interface FoodItemUiState {
    data class Success(val foodItems : List<FoodItem>) : FoodItemUiState

    object Error : FoodItemUiState
    object Loading : FoodItemUiState
}


class FoodItemViewModel : ViewModel() {

    var foodItemUiState:  FoodItemUiState by mutableStateOf(FoodItemUiState.Loading)
        private set

    init {
        getFoodItems()
    }

    fun getFoodItems() {
        viewModelScope.launch {
            foodItemUiState = FoodItemUiState.Loading
            try {
                val foodItemsResult = FoodApi.retrofitService.getFoodItems()
                foodItemUiState = FoodItemUiState.Success(foodItemsResult)
            }catch (e: Exception){
                foodItemUiState = FoodItemUiState.Error
                Log.e("FoodItemViewModel", "Error fetching food items", e)
            }

        }
    }

}


