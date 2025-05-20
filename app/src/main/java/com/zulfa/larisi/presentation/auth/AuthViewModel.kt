package com.zulfa.larisi.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfa.larisi.core.data.Resource
import com.zulfa.larisi.core.domain.model.User
import com.zulfa.larisi.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _signInState = MutableStateFlow<Resource<User>?>(null)
    val signInState: StateFlow<Resource<User>?> = _signInState.asStateFlow()

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            authUseCase.signInWithGoogle(idToken).collect { result ->
                _signInState.value = result
            }
        }
    }
}