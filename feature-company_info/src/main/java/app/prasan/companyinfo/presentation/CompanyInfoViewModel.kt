package app.prasan.companyinfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.prasan.companyinfo.mvi.CompanyInfoSideEffect
import app.prasan.companyinfo.mvi.CompanyInfoState
import app.prasan.spacexwiki.usecase.GetSpaceXCompanyInfoUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CompanyInfoViewModel(private val getSpaceXCompanyInfoUseCase: GetSpaceXCompanyInfoUseCase) :
    ViewModel(), ContainerHost<CompanyInfoState, CompanyInfoSideEffect> {

    override val container = container<CompanyInfoState, CompanyInfoSideEffect>(
        CompanyInfoState()
    )

    fun getCompanyInfo() {
        intent {
            viewModelScope.launch {
                getSpaceXCompanyInfoUseCase(false).collectLatest { result ->
                    result.onSuccess { apiResponseStr ->
                        reduce {
                            state.copy(isLoading = false, data = apiResponseStr)
                        }
                    }.onFailure {
                        reduce {
                            state.copy(isLoading = false, data = null)
                        }
                        postSideEffect(CompanyInfoSideEffect.Toast("Failure"))
                        postSideEffect(CompanyInfoSideEffect.ApiError)
                    }
                }
            }
        }
    }

    fun onBackPress() {
        intent {
            postSideEffect(CompanyInfoSideEffect.BackPress)
        }
    }
}