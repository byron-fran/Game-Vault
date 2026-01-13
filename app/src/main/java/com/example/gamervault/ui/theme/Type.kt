package com.example.gamervault.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gamervault.R

// Set of Material typography styles to start with
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.roboto_medium)
        ),
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.roboto_extra_bold)
        ),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.roboto_bold)
        ),
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.roboto_medium)
        ),
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp

    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.roboto_regular)
        ),
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp

    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(
             Font(R.font.roboto_regular)
        ),
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)