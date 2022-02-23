package com.monsieur.cloy.weatherapp.di

import androidx.room.Room
import com.monsieur.cloy.data.api.WeatherApi
import com.monsieur.cloy.data.db.CityWeatherDao
import com.monsieur.cloy.data.db.WeatherDatabase
import com.monsieur.cloy.data.repository.CityWeatherRepositoryImpl
import com.monsieur.cloy.data.repository.FavoriteCityIdRepositoryImpl
import com.monsieur.cloy.data.storage.CityWeatherStorage
import com.monsieur.cloy.data.storage.FavoriteCityIdStorage
import com.monsieur.cloy.domain.repository.CityWeatherRepository
import com.monsieur.cloy.domain.repository.FavoriteCityIdRepository
import com.monsieur.cloy.weatherapp.presentation.utilits.apiKey
import com.monsieur.cloy.weatherapp.presentation.utilits.lang
import com.monsieur.cloy.weatherapp.presentation.utilits.units
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<FavoriteCityIdStorage> {
        FavoriteCityIdStorage(context = get())
    }

    single<CityWeatherStorage> {
        CityWeatherStorage(cityWeatherDao = get(), Dispatchers.IO)
    }

    single<WeatherDatabase> {
        Room.databaseBuilder(
            androidContext(),
            WeatherDatabase::class.java,
            "weather_database")
            .createFromAsset("database/weather_database.db").build()
    }

    single<CityWeatherDao> {
        get<WeatherDatabase>().cityWeatherDao()
    }

    single<WeatherApi> {
        WeatherApi(apiKey, units, lang)
    }

    single<CityWeatherRepository> {
        CityWeatherRepositoryImpl(cityWeatherStorage = get(), weatherApi = get())
    }

    single<FavoriteCityIdRepository> {
        FavoriteCityIdRepositoryImpl(favoriteCityIdStorage = get())
    }
}