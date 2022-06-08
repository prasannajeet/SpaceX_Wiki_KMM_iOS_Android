package app.prasan.spacexwiki.compose.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import app.prasanpani.dogsapi.presentation.compose.theme.Purple200
import app.prasanpani.dogsapi.presentation.compose.theme.Purple500
import app.prasanpani.dogsapi.presentation.compose.theme.Purple700
import app.prasanpani.dogsapi.presentation.compose.theme.Teal200

val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)