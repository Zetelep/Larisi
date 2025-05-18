package com.zulfa.larisi.core.data

import com.zulfa.larisi.core.domain.model.User
import com.zulfa.larisi.core.domain.repository.IAuthRepository
import com.zulfa.larisi.core.utils.DataMapper.toUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepository(
    private val firebaseDataSource: FirebaseDataSource
) : IAuthRepository {

    override fun signInWithGoogle(idToken: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        val firebaseUser = firebaseDataSource.signInWithGoogle(idToken)

        if (firebaseUser != null) {
            emit(Resource.Success(firebaseUser.toUser()))
        } else {
            emit(Resource.Error("Sign in failed"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getCurrentUser(): User? {
        return firebaseDataSource.getCurrentUser()?.toUser()
    }

    override fun signOut() {
        firebaseDataSource.signOut()
    }

    override fun isUserSignedIn(): Boolean {
        return firebaseDataSource.isUserSignedIn()
    }
}