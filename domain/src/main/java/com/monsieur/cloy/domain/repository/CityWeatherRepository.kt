package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.AddNewCityParam
import com.monsieur.cloy.domain.models.CityWeatherInfo
import kotlinx.coroutines.flow.Flow

interface CityWeatherRepository {

    suspend fun addNewCityWeather(addNewCityParam: AddNewCityParam)

    fun getAllCityWeather(): Flow<List<CityWeatherInfo>>

    suspend fun deleteCityWeatherById(id: Int)

    suspend fun updateAllWeatherData()

//    suspend fun updateCityWeatherById(id: Int)
}