package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.CurrentWeather
import com.monsieur.cloy.data.storage.models.HourlyWeather
import com.monsieur.cloy.domain.models.CurrentWeatherInfo
import com.monsieur.cloy.domain.models.HourlyWeatherInfo

class CurrentWeatherMapper {
    fun toDomainModel(currentWeather: CurrentWeather): CurrentWeatherInfo {
        return CurrentWeatherInfo(
            currentWeather.sunrise,
            currentWeather.sunset,
            currentWeather.temp,
            currentWeather.feelsLikeTemp,
            currentWeather.pressure,
            currentWeather.humidity,
            currentWeather.clouds,
            currentWeather.uvi,
            currentWeather.visibility,
            currentWeather.windSpeed,
            currentWeather.weatherDescription,
            currentWeather.weatherIcon
        )
    }

    fun toDataModel(currentWeatherInfo: CurrentWeatherInfo): CurrentWeather {
        return CurrentWeather(
            currentWeatherInfo.sunrise,
            currentWeatherInfo.sunset,
            currentWeatherInfo.temp,
            currentWeatherInfo.feelsLikeTemp,
            currentWeatherInfo.pressure,
            currentWeatherInfo.humidity,
            currentWeatherInfo.clouds,
            currentWeatherInfo.uvi,
            currentWeatherInfo.visibility,
            currentWeatherInfo.windSpeed,
            currentWeatherInfo.weatherDescription,
            currentWeatherInfo.weatherIcon
        )
    }
}