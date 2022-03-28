package com.example.notedemoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    secondary = Color.Black,
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black
)

private val LightColorPalette = lightColors(
    primary = Blue500,
    secondary = Blue500,
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Blue500
)

@Composable
fun NoteDemoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}