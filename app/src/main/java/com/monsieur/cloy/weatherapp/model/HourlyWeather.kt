package com.monsieur.cloy.weatherapp.model

import com.monsieur.cloy.weatherapp.api.classes.Hourly
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

class HourlyWeather(hourly: Hourly, timezoneOffset: Int) {

    var time: LocalTime//Время прогнозируемых данных, Unix, UTC

    var temp: Double//Температура

    var humidity: Int// Влажность, %

    var pop: Double// Вероятность осадков

    var weatherDescription: String = ""

    var weatherIcon: String = ""

    init {
        time = LocalDateTime.ofEpochSecond((hourly.dt + timezoneOffset).toLong(), 0, ZoneOffset.UTC).toLocalTime()
        temp = hourly.temp
        humidity = hourly.humidity
        pop = hourly.pop
        if(hourly.weather.isNotEmpty()) {
            weatherDescription = hourly.weather[0].description
            weatherIcon = hourly.weather[0].icon
        }
    }
}