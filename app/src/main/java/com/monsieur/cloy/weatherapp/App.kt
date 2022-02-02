package com.monsieur.cloy.weatherapp

import android.app.Application
import android.content.Context
import com.monsieur.cloy.weatherapp.di.AppComponent
import com.monsieur.cloy.weatherapp.di.AppModule
import com.monsieur.cloy.weatherapp.di.DaggerAppComponent
import com.monsieur.cloy.weatherapp.di.RoomModule

class App: Application() {

    lateinit var appComponent: AppComponent
    private set

    companion object{
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }