package app.prasan.spacexwiki.compose.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.CollapsingToolbarScaffoldScope
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun CollaspingToolbarWithBack(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
    body: @Composable CollapsingToolbarScaffoldScope.() -> Unit
) {
    val state = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier.background(if (isSystemInDarkTheme()) Color.White else Color.Black),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            val textSize = (16 + (30 - 16) * state.toolbarState.progress).sp
            val paddingSize = (64 - (108 - 64) * state.toolbarState.progress).dp
            // 700 when expanded, 400 when collapsed
            val fontWeight = FontWeight(
                (FontWeight.Normal.weight + (FontWeight.Bold.weight - FontWeight.Normal.weight) * state.toolbarState.progress.toInt())
            )

            Box(
                modifier = Modifier
                    .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
                    .fillMaxWidth()
                    .height(120.dp)
                    .pin()
            )

            Text(
                text = title,
                modifier = Modifier
                    .road(Alignment.CenterStart, Alignment.BottomStart)
                    .padding(paddingSize, 16.dp, 16.dp, 16.dp),
                fontSize = textSize,
                fontWeight = fontWeight,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontFamily = FontFamily.Default
            )

            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        },
        body = body
    )
}