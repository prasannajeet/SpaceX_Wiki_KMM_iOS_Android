package app.prasan.spacexwiki.compose.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.prasan.spacexwiki.utils.ViewState

@Composable
fun <Data: Any> StatefulDisplayView(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = isSystemInDarkTheme(),
    state: ViewState<Data>,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    idleView: @Composable (Modifier) -> Unit,
    loadingView: @Composable (Modifier) -> Unit,
    errorView: @Composable (Any, Modifier) -> Unit,
    stateRenderView: @Composable (Data, Modifier) -> Unit
) {
    val viewModifier = modifier.then(Modifier.padding(top = topPadding, bottom = bottomPadding))
    when(state) {
        ViewState.Idle -> idleView(viewModifier)
        ViewState.Loading -> loadingView(viewModifier)
        is ViewState.Success -> stateRenderView(state.state, viewModifier)
        is ViewState.Error -> errorView(state.state, viewModifier)
    }
}