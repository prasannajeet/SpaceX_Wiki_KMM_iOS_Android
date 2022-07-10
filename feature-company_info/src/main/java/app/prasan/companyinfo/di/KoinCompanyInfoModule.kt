package app.prasan.companyinfo.di

import app.prasan.companyinfo.presentation.CompanyInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureCompanyInfoModule = module {
    viewModelOf(::CompanyInfoViewModel)
}