package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.CityWeather
import com.monsieur.cloy.data.storage.models.CurrentWeather
import com.monsieur.cloy.domain.models.CityWeatherInfo
import com.monsieur.cloy.domain.models.CurrentWeatherInfo
import com.monsieur.cloy.domain.models.DailyWeatherInfo
import com.monsieur.cloy.domain.models.HourlyWeatherInfo

class CityWeatherMapper {
    fun toDomainModel(cityWeather: CityWeather): CityWeatherInfo {
        val currentWeatherInfo = if (cityWeather.currentWeather == null) {
            null
        } else {
            CurrentWeatherMapper().toDomainModel(cityWeather.currentWeather!!)
        }
        return CityWeatherInfo(
            cityWeather.id,
            cityWeather.latitude,
            cityWeather.longitude,
            cityWeather.timezoneOffset,
            cityWeather.cityName,
            cityWeather.region,
            cityWeather.lastUpdateTimeUTC,
            currentWeatherInfo,
            HourlyWeatherMapper().toListDomainModel(cityWeather.hourlyWeather),
            DailyWeatherMapper().toListDomainModel(cityWeather.dailyWeather)
        )
    }

    fun toDataModel(cityWeather: CityWeatherInfo): CityWeather {
        val currentWeather = if (cityWeather.currentWeather == null) {
            null
        } else {
            CurrentWeatherMapper().toDataModel(cityWeather.currentWeather!!)
        }
        return CityWeather(
            cityWeather.id,
            cityWeather.latitude,
            cityWeather.longitude,
            cityWeather.timezoneOffset,
            cityWeather.cityName,
            cityWeather.region,
            cityWeather.lastUpdateTimeUTC,
            currentWeather,
            HourlyWeatherMapper().toListDataModel(cityWeather.hourlyWeather),
            DailyWeatherMapper().toListDataModel(cityWeather.dailyWeather)
        )
    }
}