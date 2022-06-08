package app.prasan.spacexwiki.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import app.prasan.spacexwiki.compose.theme.DarkColorPalette
import app.prasan.spacexwiki.compose.theme.LightColorPalette
import app.prasan.spacexwiki.compose.theme.Shapes
import app.prasan.spacexwiki.compose.theme.Typography

@Composable
fun SpaceXWikiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
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