package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.DailyWeather
import com.monsieur.cloy.domain.models.DailyWeatherInfo

class DailyWeatherMapper {
    fun toDomainModel(dailyWeather: DailyWeather): DailyWeatherInfo {
        return DailyWeatherInfo(
            dailyWeather.date,
            dailyWeather.nightTemp,
            dailyWeather.dayTemp,
            dailyWeather.humidity,
            dailyWeather.pop,
            dailyWeather.weatherDescription,
            dailyWeather.weatherDescription
        )
    }

    fun toListDomainModel(listDailyWeather: List<DailyWeather>): List<DailyWeatherInfo>{
        val list = ArrayList<DailyWeatherInfo>()
        listDailyWeather.forEach {
            list.add(
                DailyWeatherMapper().toDomainModel(
                    it
                )
            )
        }
        return list
    }

    fun toListDataModel(listDailyWeatherInfo: List<DailyWeatherInfo>): List<DailyWeather>{
        val list = ArrayList<DailyWeather>()
        listDailyWeatherInfo.forEach {
            list.add(
                DailyWeatherMapper().toDataModel(
                    it
                )
            )
        }
        return list
    }

    fun toDataModel(dailyWeatherInfo: DailyWeatherInfo): DailyWeather {
        return DailyWeather(
            dailyWeatherInfo.date,
            dailyWeatherInfo.nightTemp,
            dailyWeatherInfo.dayTemp,
            dailyWeatherInfo.humidity,
            dailyWeatherInfo.pop,
            dailyWeatherInfo.weatherDescription,
            dailyWeatherInfo.weatherIcon)
    }
}