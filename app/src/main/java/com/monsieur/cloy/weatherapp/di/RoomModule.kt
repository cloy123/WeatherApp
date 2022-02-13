package com.monsieur.cloy.weatherapp.di

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import com.monsieur.cloy.weatherapp.database.Database
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeatherDao
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Module
class RoomModule(private val application: Application) {

    private val database: Database = Room.databaseBuilder(application, Database::class.java, "database")
        .build()

    @Singleton
    @Provides
    fun provideAppDatabase(): Database{
        return database
    }

    @Singleton
    @Provides
    fun providesCityWeatherDao(database: Database): CityWeatherDao{
        return database.cityWeatherDao()
    }

    @Singleton
    @Provides
    fun providesCityWeatherRepository(cityWeatherDao: CityWeatherDao): CityWeatherRepository{
        return CityWeatherRepository(cityWeatherDao)
    }
}