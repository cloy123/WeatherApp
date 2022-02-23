package com.monsieur.cloy.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.monsieur.cloy.data.storage.models.CityWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface CityWeatherDao {

    @Insert
    suspend fun insertCityWeather(cityWeather: CityWeather): Long

    @Update
    suspend fun updateCityWeather(cityWeather: CityWeather)

    @Update
    suspend fun updateCityWeather(listCityWeather: List<CityWeather>)

    @Query("DELETE FROM cityWeather WHERE id = :id")
    suspend fun deleteCityWeatherById(id: Int)

    @Query("SELECT * FROM cityWeather")
    fun getAllCityWeather(): Flow<List<CityWeather>>
}