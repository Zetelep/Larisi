package com.zulfa.larisi.core.domain.repository

import com.zulfa.larisi.core.data.Resource
import com.zulfa.larisi.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository{
    fun signInWithGoogle(idToken: String): Flow<Resource<User>>
    fun getCurrentUser(): User?
    fun signOut()
    fun isUserSignedIn(): Boolean
}