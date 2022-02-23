package com.monsieur.cloy.data.repository

import android.util.Log
import com.monsieur.cloy.data.api.WeatherApi
import com.monsieur.cloy.data.mappers.CityWeatherMapper
import com.monsieur.cloy.data.storage.CityWeatherStorage
import com.monsieur.cloy.data.storage.models.CityWeather
import com.monsieur.cloy.domain.models.AddNewCityParam
import com.monsieur.cloy.domain.models.CityWeatherInfo
import com.monsieur.cloy.domain.repository.CityWeatherRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class CityWeatherRepositoryImpl(
    private val cityWeatherStorage: CityWeatherStorage,
    private val weatherApi: WeatherApi
) : CityWeatherRepository {

    override suspend fun addNewCityWeather(addNewCityParam: AddNewCityParam) {
        withContext(Dispatchers.IO) {
            val cityWeather = CityWeather(
                addNewCityParam.latitude,
                addNewCityParam.longitude,
                addNewCityParam.cityName,
                addNewCityParam.region
            )
            cityWeather.id = cityWeatherStorage.insert(cityWeather).toInt()
            try {
                val response =
                    weatherApi.requestWeatherData(cityWeather.latitude, cityWeather.longitude)
                if (response.isSuccessful && response.body() != null) {
                    cityWeather.updateWeatherData(response.body()!!)
                }
            }catch (e: Exception){
                Log.d("my_log", e.toString())
            }
            cityWeatherStorage.update(cityWeather)
        }
    }

    override fun getAllCityWeather(): Flow<List<CityWeatherInfo>> {
        return cityWeatherStorage.getAll().map { list ->
            list.map {
                CityWeatherMapper().toDomainModel(it)
            }
        }
    }

    override suspend fun deleteCityWeatherById(id: Int) {
        cityWeatherStorage.delete(id)
    }

    override suspend fun updateAllWeatherData() {
        withContext(Dispatchers.IO){
            val cities = cityWeatherStorage.getAll().first()
            for (city in cities) {
                if (city.lastUpdateTimeUTC == null || LocalDateTime.ofEpochSecond(
                        System.currentTimeMillis(),
                        0,
                        ZoneOffset.UTC
                    ).minusHours(1) > city.lastUpdateTimeUTC
                ) {
                    try {
                        val response = weatherApi.requestWeatherData(city.latitude, city.longitude)
                        if (response.isSuccessful && response.body() != null) {
                            city.updateWeatherData(response.body()!!)
                        } else {
                            Log.d("my_log", "MESSAGE - " + response.message())
                            Log.d("my_log", "CODE - " + response.code().toString())
                            Log.d("my_log", "ERROR_BODY - " + response.errorBody()?.toString())
                        }
                    }catch (e: Exception){
                        Log.d("my_log", "exception - $e")
                    }
                }
            }
            cityWeatherStorage.update(cities)
        }
    }

//    override suspend fun updateCityWeatherById(id: Int) {
//        TODO("Not yet implemented")
//    }
}