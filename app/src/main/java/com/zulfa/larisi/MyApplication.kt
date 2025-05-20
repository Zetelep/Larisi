package com.zulfa.larisi

import android.app.Application
import com.zulfa.larisi.core.di.authModule
import com.zulfa.larisi.presentation.di.authUseCase
import com.zulfa.larisi.presentation.di.helperModule
import com.zulfa.larisi.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    authModule,
                    viewModelModule,
                    helperModule,
                    authUseCase
                )
            )
        }
    }
}