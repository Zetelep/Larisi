package com.zulfa.larisi.core.utils

import com.google.firebase.auth.FirebaseUser
import com.zulfa.larisi.core.domain.model.User

object DataMapper {
    fun FirebaseUser.toUser(): User {
        return User(
            userId = uid,
            username = displayName ?: "Unknown",
            email = email ?: "",
            profilePictureUrl = photoUrl?.toString()
        )
    }
}