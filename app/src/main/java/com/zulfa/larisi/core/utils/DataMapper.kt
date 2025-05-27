package com.zulfa.larisi.core.utils

import com.google.firebase.auth.FirebaseUser
import com.zulfa.larisi.core.data.MenuItemResponse
import com.zulfa.larisi.core.domain.model.MenuItem
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

    fun MenuItemResponse.toDomain(id: String): MenuItem {
        return MenuItem(
            id = id,
            name = name.orEmpty(),
            price = price ?: 0.0,
            imageLink = imageLink.orEmpty()
        )
    }
}