package app.prasan.spacexwiki.viewmodel

import app.prasan.spacexwiki.contract.MVIViewModel
import app.prasan.spacexwiki.expectactual.ViewModel
import app.prasan.spacexwiki.usecase.GetSpaceXCompanyInfoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val companyInfoUseCase: GetSpaceXCompanyInfoUseCase): ViewModel(), MVIViewModel<String, String> {
    private val _state by lazy { MutableStateFlow("I am initial state") }
    override val state: StateFlow<String>
        get() = _state

    private val _event by lazy { MutableSharedFlow<String>() }
    override val event: SharedFlow<String>
        get() = _event

    override fun updateState(event: String, previousState: String) {
        viewModelScope.launch {
            _state.update { "I am getting value" }
            delay(2000)
            companyInfoUseCase(Unit).collectLatest {
                it.onSuccess { result ->
                    _state.update { result }
                }.onFailure { error ->
                    _state.update { "Error with message ${error.message}" }
                }
            }
        }
    }
}