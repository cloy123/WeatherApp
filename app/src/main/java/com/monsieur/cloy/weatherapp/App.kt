package com.monsieur.cloy.weatherapp

import android.app.Application
import com.monsieur.cloy.weatherapp.di.appModule
import com.monsieur.cloy.weatherapp.di.dataModule
import com.monsieur.cloy.weatherapp.di.domainModule
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        System.setProperty(DEBUG_PROPERTY_NAME, DEBUG_PROPERTY_VALUE_ON)
        startKoin {
            androidLogger(Level.ERROR)
            modules(listOf(appModule, domainModule, dataModule))
            androidContext(this@App)
        }
    }
}