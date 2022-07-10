package app.prasan.androidcore.compose.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.prasan.spacexwiki.utils.ViewState

@Composable
fun <StateObject : Any> StatefulDisplayView(
    modifier: Modifier = Modifier,
    state: ViewState<StateObject>,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    idleView: @Composable (Modifier) -> Unit,
    loadingView: @Composable (Modifier) -> Unit,
    stateRenderView: @Composable (StateObject, Modifier) -> Unit
) {
    val viewModifier = modifier.then(Modifier.padding(top = topPadding, bottom = bottomPadding))
    when (state) {
        ViewState.Idle -> idleView(viewModifier)
        ViewState.Loading -> loadingView(viewModifier)
        is ViewState.RenderState -> stateRenderView(state.state, viewModifier)
    }
}