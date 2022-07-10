package app.prasan.companyinfo.mvi

sealed class CompanyInfoSideEffect {
    data class Toast(val text: String) : CompanyInfoSideEffect()
    object BackPress : CompanyInfoSideEffect()
    object ApiError : CompanyInfoSideEffect()
}