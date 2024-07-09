package com.example.foodorder

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodorder.design.BottomNavigationFunction
import com.example.foodorder.design.CookItemScreen
import com.example.foodorder.design.DeviceScreen
import com.example.foodorder.design.FavouritesScreen
import com.example.foodorder.design.ManualScreen
import com.example.foodorder.design.PreferencesScreen
import com.example.foodorder.design.SettingScreen
import com.example.foodorder.ui.theme.FoodOrderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            FoodOrderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationFunction(navController = navController)
                        }
                    ){innerPadding ->

                        NavHost(
                            navController = navController,
                            startDestination = Screen.Cook.name,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(route = Screen.Cook.name){
                                CookItemScreen(
                                    //navController = navController
                                )
                            }

                            composable(route = Screen.Favourites.name){
                                FavouritesScreen()
                            }

                            composable(route = Screen.Manual.name){
                                ManualScreen()
                            }

                            composable(route = Screen.Device.name){
                                DeviceScreen()
                            }

                            composable(route = Screen.Preferences.name){
                                PreferencesScreen()
                            }

                            composable(route = Screen.Setting.name){
                                SettingScreen()
                            }
                        }

                    }

                }
            }
        }
    }
}

enum class Screen(val iconResId : Int){
    Cook(R.drawable.cook),
    Favourites(R.drawable.favourites),
    Manual(R.drawable.manual2),
    Device(R.drawable.device),
    Preferences(R.drawable.preferences),
    Setting(R.drawable.settings)
}