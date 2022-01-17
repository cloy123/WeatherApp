package com.monsieur.cloy.weatherapp.model.cityWeather

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityWeatherDao {

    @Insert
    suspend fun insertCityWeather(cityWeather: CityWeather)

    @Update
    suspend fun updateCityWeather(cityWeather: CityWeather)

    @Delete
    suspend fun deleteCityWeather(cityWeather: CityWeather)

    @Query("SELECT * FROM cityWeather")
    suspend fun getAllCityWeather(): LiveData<List<CityWeather>>
}