package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.AddNewCityParam
import com.monsieur.cloy.domain.models.CityWeatherInfo
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface CityWeatherRepository {

    @DelicateCoroutinesApi
    suspend fun addNewCityWeather(addNewCityParam: AddNewCityParam)

    fun getAllCityWeather(): Flow<List<CityWeatherInfo>>

    suspend fun deleteCityWeatherById(id: Int)

    @DelicateCoroutinesApi
    suspend fun updateAllWeatherData()
}