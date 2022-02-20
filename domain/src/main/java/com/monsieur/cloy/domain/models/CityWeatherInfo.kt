package com.monsieur.cloy.domain.models

import java.time.LocalDateTime

class CityWeatherInfo(
    var id: Int,
    var latitude: Double,
    var longitude: Double,
    var timezoneOffset: Int,
    var cityName: String,
    var region: String,
    var lastUpdateTimeUTC: LocalDateTime?,
    var currentWeather: CurrentWeatherInfo?,
    var hourlyWeather: List<HourlyWeatherInfo>,
    var dailyWeather: List<DailyWeatherInfo>
) {
}