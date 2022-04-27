package com.sokolovds.pecodeandroidtask.core

import android.app.Application
import com.sokolovds.pecodeandroidtask.core.di.dataModule
import com.sokolovds.pecodeandroidtask.core.di.domainModule
import com.sokolovds.pecodeandroidtask.core.di.appViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appViewModels, dataModule, domainModule))
        }
    }
}