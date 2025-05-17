package com.zulfa.larisi.core.domain.usecase

import com.zulfa.larisi.core.data.AuthRepository
import com.zulfa.larisi.core.data.Resource
import com.zulfa.larisi.core.domain.model.User
import kotlinx.coroutines.flow.Flow

class AuthInteractor(
    private val repository: AuthRepository
) : AuthUseCase {

    override fun signInWithGoogle(idToken: String): Flow<Resource<User>> {
        return repository.signInWithGoogle(idToken)
    }

    override fun getCurrentUser(): User? {
        return repository.getCurrentUser()
    }

    override fun signOut() {
        repository.signOut()
    }
}