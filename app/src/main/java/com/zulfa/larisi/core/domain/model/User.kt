package com.zulfa.larisi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userId: String,
    val username: String?,
    val email: String,
    val profilePictureUrl: String?,
):Parcelable