package app.prasan.spacexwiki.utils

sealed class ViewState<StateObject : Any> {
    object Idle : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
    data class Success<StateObject : Any>(val state: StateObject) : ViewState<StateObject>()
    data class Error<StateObject : Any>(val state: StateObject) : ViewState<StateObject>()
}