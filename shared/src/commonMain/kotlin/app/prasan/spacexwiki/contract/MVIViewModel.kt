package app.prasan.spacexwiki.contract

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MVIViewModel<State: Any, Event: Any> {
    val state: StateFlow<State>
    val event: SharedFlow<Event>

    fun updateState(event: Event, previousState: State)
}