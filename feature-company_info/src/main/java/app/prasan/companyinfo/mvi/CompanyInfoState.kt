package app.prasan.companyinfo.mvi

import app.prasan.spacexwiki.models.dao.CompanyInfo

data class CompanyInfoState(
    val isLoading: Boolean = true,
    val data: CompanyInfo? = null,
)