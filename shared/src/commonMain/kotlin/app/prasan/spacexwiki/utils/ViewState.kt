package app.prasan.spacexwiki.utils

sealed class ViewState<StateObject : Any> {
    object Idle : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
    data class RenderState<StateObject : Any>(val state: StateObject) : ViewState<StateObject>()
}