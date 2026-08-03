package com.example.docreader.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = ExecutiveRoyalNavy,
    secondary = ComfortingTeal,
    background = OnSurfaceCharcoal,
    surface = Color(0xFF1E293B),
    onBackground = SoftSlateGray,
    onSurface = SoftSlateGray,
    error = ErrorRuby
)

private val LightColorScheme = lightColorScheme(
    primary = ExecutiveRoyalNavy,
    secondary = ComfortingTeal,
    background = SoftSlateGray,
    surface = Color.White,
    onBackground = OnSurfaceCharcoal,
    onSurface = OnSurfaceCharcoal,
    error = ErrorRuby
)

@Composable
fun DocReaderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
