package app.prasan.spacexwiki.android.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.prasan.androidcore.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        onTimeout()
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(450.dp)
                .alpha(alpha = alpha),
            painter = painterResource(id = R.drawable.spacex_logo),
            contentDescription = "Logo Icon",
            tint = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
    }
}

@Preview(name = "Splash Screen Dark", uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun SplashScreenDark() {
    Splash(alpha = 1.0f)
}

@Preview(name = "Splash Screen Light", uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun SplashScreenLight() {
    Splash(alpha = 1.0f)
}