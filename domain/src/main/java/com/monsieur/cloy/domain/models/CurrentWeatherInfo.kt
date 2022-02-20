package com.monsieur.cloy.domain.models

import java.time.*

class CurrentWeatherInfo(var sunrise: LocalTime,
                         var sunset: LocalTime,
                         var temp: Double,
                         var feelsLikeTemp: Double,
                         var pressure: Int,
                         var humidity: Int,
                         var clouds: Int,
                         var uvi: Double,
                         var visibility: Int,
                         var windSpeed: Double,
                         var weatherDescription: String,
                         var weatherIcon: String) {
}