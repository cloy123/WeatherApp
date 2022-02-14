package com.monsieur.cloy.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.monsieur.cloy.weatherapp.database.Database
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeatherDao
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.monsieur.cloy.weatherapp.model.chosenCity.ChosenCityDao
import com.monsieur.cloy.weatherapp.model.chosenCity.ChosenCityRepository


@Module
class RoomModule(application: Application) {

    val application = application

    @Singleton
    @Provides
    fun provideAppDatabase() =
        Room.databaseBuilder(application, Database::class.java, "database")
            //.createFromAsset("database/database.db")
            .build()


    @Singleton
    @Provides
    fun providesCityWeatherDao(database: Database): CityWeatherDao {
        return database.cityWeatherDao()
    }

    @Singleton
    @Provides
    fun providesCityWeatherRepository(cityWeatherDao: CityWeatherDao): CityWeatherRepository {
        return CityWeatherRepository(cityWeatherDao)
    }

    @Singleton
    @Provides
    fun providesChosenCityDao(database: Database): ChosenCityDao {
        return database.chosenCityDap()
    }

    @Singleton
    @Provides
    fun providesChosenCityRepository(chosenCityDao: ChosenCityDao): ChosenCityRepository {
        return ChosenCityRepository(chosenCityDao)
    }
}