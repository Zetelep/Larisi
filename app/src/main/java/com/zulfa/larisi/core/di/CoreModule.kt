package com.zulfa.larisi.core.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.zulfa.larisi.core.data.AuthRepository
import com.zulfa.larisi.core.data.FirebaseDataSource
import com.zulfa.larisi.core.data.FirestoreDataSource
import com.zulfa.larisi.core.data.MenuItemRepository
import com.zulfa.larisi.core.domain.repository.IAuthRepository
import com.zulfa.larisi.core.domain.repository.IMenuRepository


import org.koin.dsl.module

val authModule = module {

    // Firebase Auth
    single { Firebase.auth }

    // DataSource
    single { FirebaseDataSource(get()) }

    // Repository
    single<IAuthRepository> { AuthRepository(get()) }
}

val menuFirestoreModule = module {
    single { FirebaseFirestore.getInstance() }

    single { FirestoreDataSource(get(), get()) }

    single<IMenuRepository> {MenuItemRepository(get()) }
}