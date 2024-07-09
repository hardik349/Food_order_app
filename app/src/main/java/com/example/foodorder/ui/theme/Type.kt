package com.example.foodorder.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.foodorder.R

val JockeyOne = FontFamily(
    Font(R.raw.jockeyone_regular)
)

val JostFont = FontFamily(
    Font(R.raw.jost_medium)
)

val AbhayaLibre = FontFamily(
    Font(R.raw.abhaya_libre)
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    //For JockeyOne
    displayLarge = TextStyle(
        fontFamily = JockeyOne,
        fontWeight = FontWeight.Bold
    ),

    //For JoshFont
    displayMedium = TextStyle(
        fontFamily = JostFont,
    ),

    //For AbhayaLibre
    displaySmall = TextStyle(
        fontFamily = AbhayaLibre
    )
)
