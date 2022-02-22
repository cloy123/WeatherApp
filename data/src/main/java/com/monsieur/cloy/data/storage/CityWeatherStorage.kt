package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.CityWeatherDao
import com.monsieur.cloy.data.storage.models.CityWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CityWeatherStorage(
    private val cityWeatherDao: CityWeatherDao,
    private val dispatcher: CoroutineDispatcher,
) {

    fun getAll(): Flow<List<CityWeather>> {
        return cityWeatherDao.getAllCityWeather()
    }

    suspend fun delete(id: Int) {
        withContext(dispatcher){
            cityWeatherDao.deleteCityWeatherById(id)
        }
    }

    suspend fun insert(cityWeather: CityWeather) {
        withContext(dispatcher){
            cityWeatherDao.insertCityWeather(cityWeather)
        }
    }

    suspend fun update(cityWeather: CityWeather) {
        withContext(dispatcher){
            cityWeatherDao.updateCityWeather(cityWeather)
        }
    }

    suspend fun update(listCityWeather: List<CityWeather>){
        withContext(dispatcher){
            cityWeatherDao.updateCityWeather(listCityWeather)
        }
    }
}