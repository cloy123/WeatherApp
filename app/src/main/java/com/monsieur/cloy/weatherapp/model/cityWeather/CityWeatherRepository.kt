package com.monsieur.cloy.weatherapp.model.cityWeather

import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityWeatherRepository @Inject constructor(val cityWeatherDao: CityWeatherDao) {

    fun getAllCityWeather(): LiveData<List<CityWeather>> = cityWeatherDao.getAllCityWeather()

    suspend fun insertCityWeather(cityWeather: CityWeather){
        cityWeatherDao.insertCityWeather(cityWeather)
    }

    suspend fun updateCityWeather(cityWeather: CityWeather){
        cityWeatherDao.updateCityWeather(cityWeather)
    }

    suspend fun deleteCityWeather(cityWeather: CityWeather){
        cityWeatherDao.deleteCityWeather(cityWeather)
    }
}