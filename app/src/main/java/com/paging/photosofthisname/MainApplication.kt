package com.paging.photosofthisname

import android.app.Application
import com.paging.photosofthisname.di.networkModules
import com.paging.photosofthisname.di.repositoryModule
import com.paging.photosofthisname.di.useCaseModules
import com.paging.photosofthisname.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(viewModelModule, networkModules, useCaseModules, repositoryModule))
        }
    }
}