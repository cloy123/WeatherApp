package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.HourlyWeather
import com.monsieur.cloy.domain.models.HourlyWeatherInfo

class HourlyWeatherMapper {
    fun toDomainModel(hourlyWeather: HourlyWeather): HourlyWeatherInfo {
        return HourlyWeatherInfo(
            hourlyWeather.time,
            hourlyWeather.temp,
            hourlyWeather.humidity,
            hourlyWeather.pop,
            hourlyWeather.weatherDescription,
            hourlyWeather.weatherIcon
        )
    }

    fun toDataModel(hourlyWeatherInfo: HourlyWeatherInfo): HourlyWeather {
        return HourlyWeather(
            hourlyWeatherInfo.time,
            hourlyWeatherInfo.temp,
            hourlyWeatherInfo.humidity,
            hourlyWeatherInfo.pop,
            hourlyWeatherInfo.weatherDescription,
            hourlyWeatherInfo.weatherIcon
        )
    }

    fun toListDomainModel(listHourlyWeather: List<HourlyWeather>): List<HourlyWeatherInfo>{
        val list = ArrayList<HourlyWeatherInfo>()
        listHourlyWeather.forEach {
            list.add(
                HourlyWeatherMapper().toDomainModel(
                    it
                )
            )
        }
        return list
    }

    fun toListDataModel(listHourlyWeatherInfo: List<HourlyWeatherInfo>): List<HourlyWeather>{
        val list = ArrayList<HourlyWeather>()
        listHourlyWeatherInfo.forEach {
            list.add(
                HourlyWeatherMapper().toDataModel(
                    it
                )
            )
        }
        return list
    }
}