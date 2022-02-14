package com.monsieur.cloy.weatherapp.di

import android.app.Application
import com.monsieur.cloy.weatherapp.database.Database
import com.monsieur.cloy.weatherapp.model.chosenCity.ChosenCityDao
import com.monsieur.cloy.weatherapp.model.chosenCity.ChosenCityRepository
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeatherDao
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeatherRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [], modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    val cityWeatherDao: CityWeatherDao
    val cityWeatherRepository: CityWeatherRepository

    val chosenCityDao: ChosenCityDao
    val chosenCityRepository: ChosenCityRepository

    val database: Database
    val application: Application
}