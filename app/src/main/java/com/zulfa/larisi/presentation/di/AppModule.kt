package com.zulfa.larisi.presentation.di

import com.zulfa.larisi.core.domain.usecase.AuthInteractor
import com.zulfa.larisi.core.domain.usecase.AuthUseCase
import com.zulfa.larisi.presentation.auth.AuthViewModel
import com.zulfa.larisi.presentation.helper.CredentialHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
}

val helperModule = module {
    single { CredentialHelper(get()) }
}

val authUseCase = module {
    factory<AuthUseCase> { AuthInteractor(get()) }
}