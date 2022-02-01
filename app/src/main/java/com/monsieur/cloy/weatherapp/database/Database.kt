package com.monsieur.cloy.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeather
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeatherDao

@Database(entities = [CityWeather::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun cityWeatherDao(): CityWeatherDao
}