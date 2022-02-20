package com.monsieur.cloy.domain.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class DailyWeatherInfo(var date: LocalDate,
                       var nightTemp: Double,
                       var dayTemp: Double,
                       var humidity: Int,
                       var pop: Int,
                       var weatherDescription: String,
                       var weatherIcon: String) {
}