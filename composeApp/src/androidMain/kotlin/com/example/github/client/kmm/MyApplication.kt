package com.example.github.client.kmm

import android.app.Application
import com.example.github.client.kmm.module.androidModule
import com.example.github.client.kmm.module.sharedModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(sharedModule + androidModule)
        }
    }
}