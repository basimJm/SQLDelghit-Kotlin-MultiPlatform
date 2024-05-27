package com.blackhand.sqldelight.android.app

import android.app.Application
import com.blackhand.sqldelight.android.data.di.AndroidModule
import com.blackhand.sqldelight.data.di.initKoin
import com.blackhand.sqldelight.data.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidApp)
            modules(initKoin(viewModelModule = AndroidModule, sqlDriverModule = platformModule))
        }
    }
}
