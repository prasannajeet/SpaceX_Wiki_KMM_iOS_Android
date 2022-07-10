package app.prasan.androidcore.compose.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.prasan.androidcore.R

data class DragonThemeTypography(
    val screenHeader: TextStyle,
    val sectionHeader: TextStyle,
    val title: TextStyle,
    val subTitle: TextStyle,
    val body: TextStyle,
)

internal val robotoFontFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.W100),
    Font(R.font.roboto_medium, FontWeight.W400),
)

val dragonThemeTypography =
    DragonThemeTypography(

        screenHeader = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
        ),

        sectionHeader = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.sp,
        ),

        title = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.sp,
        ),

        subTitle = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.sp,
        ),

        body = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.sp,
        ),
    )

val TextStyle.emphasise get() = this.copy(fontWeight = FontWeight.W400)

val LocalTypography = staticCompositionLocalOf { dragonThemeTypography }