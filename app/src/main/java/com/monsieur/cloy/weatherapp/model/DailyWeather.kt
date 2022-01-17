package com.monsieur.cloy.weatherapp.model

import com.monsieur.cloy.weatherapp.api.classes.Daily
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class DailyWeather(daily: Daily, timezoneOffset: Int) {

    var date: LocalDate//Время прогнозируемых данных, Unix, UTC

    var nightTemp: Double//Ночная температура.

    var dayTemp: Double//Дневная температура.

    var humidity: Int//Влажность, %

    var pop: Double//Вероятность осадков

    var weatherDescription: String = ""

    var weatherIcon: String = ""

    init {
        date = LocalDateTime.ofEpochSecond((daily.dt + timezoneOffset).toLong(), 0, ZoneOffset.UTC).toLocalDate()
        nightTemp = daily.temp.night
        dayTemp = daily.temp.day
        humidity = daily.humidity
        pop = daily.pop
        if(daily.weather.isNotEmpty()) {
            weatherDescription = daily.weather[0].description
            weatherIcon = daily.weather[0].icon
        }
    }
}