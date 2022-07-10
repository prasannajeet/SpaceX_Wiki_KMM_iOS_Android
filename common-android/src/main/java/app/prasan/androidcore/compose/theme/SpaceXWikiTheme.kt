package app.prasan.spacexwiki.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import app.prasan.androidcore.compose.theme.DragonTheme
import app.prasan.androidcore.compose.theme.dragonThemeDefaultDarkColors
import app.prasan.androidcore.compose.theme.dragonThemeDefaultLightColors
import app.prasan.androidcore.compose.theme.dragonThemeTypography


@Composable
fun SpaceXWikiTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    // Using default ones but colors and typography can be replaced with desired colors and text styles
    val colors = if (darkTheme) {
        dragonThemeDefaultDarkColors
    } else {
        dragonThemeDefaultLightColors
    }

    DragonTheme(
        colors = colors,
        typography = dragonThemeTypography,
        content = content
    )
}