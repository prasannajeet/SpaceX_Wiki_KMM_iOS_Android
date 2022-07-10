package app.prasan.androidcore.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun DragonTheme(
    colors: DragonThemeColours = DragonTheme.colors,
    typography: DragonThemeTypography = DragonTheme.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
    ) {
        content()
    }
}

object DragonTheme {

    // Retrieves the current colors
    val colors: DragonThemeColours
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    // Retrieves the current typography
    val typography: DragonThemeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}