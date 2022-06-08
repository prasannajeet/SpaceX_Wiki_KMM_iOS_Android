package app.prasan.spacexwiki

import app.prasan.spacexwiki.expectactual.CallbackViewModel
import app.prasan.spacexwiki.expectactual.ViewModel
import app.prasan.spacexwiki.usecase.GetSpaceXCompanyInfoUseCase
import app.prasan.spacexwiki.viewmodel.MainViewModel
import co.touchlab.kermit.Logger

class MainCallbackViewModel(private val useCase: GetSpaceXCompanyInfoUseCase): CallbackViewModel<MainViewModel>() {
    override fun getViewModel(): MainViewModel {
        return MainViewModel(useCase)
    }

    val getCurrentTextState = getViewModel().state.asCallbacks()

    fun callApi() {
        getViewModel().updateState("", "")
    }
}