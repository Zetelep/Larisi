package com.zulfa.larisi.core.domain.usecase

import com.zulfa.larisi.core.data.Resource
import com.zulfa.larisi.core.domain.model.User
import com.zulfa.larisi.core.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class AuthInteractor(
    private val repository: IAuthRepository
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

    override fun isUserSignedIn(): Boolean {
        return repository.isUserSignedIn()
    }
}