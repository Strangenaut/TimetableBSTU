package com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Black2,
    onPrimary = Color.White,

    secondary = Blue700,
    onSecondary = TranslucentWhite,

    surface = Black1,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = White1,
    primaryVariant = White2,
    onPrimary = Color.Black,

    secondary = Blue700,
    onSecondary = TranslucentBlack,

    surface = Color.White,
    onSurface = Color.Black
)

var isDarkThemeEnabled = false

@Composable
fun TimetableBSTUTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    isDarkThemeEnabled = darkTheme
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