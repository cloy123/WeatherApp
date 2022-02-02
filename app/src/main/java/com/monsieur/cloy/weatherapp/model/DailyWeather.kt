package com.monsieur.cloy.weatherapp.model

import com.monsieur.cloy.weatherapp.api.classes.Daily
import com.monsieur.cloy.weatherapp.utilits.getDayOfWeek
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class DailyWeather(daily: Daily, timezoneOffset: Int) {

    var date: LocalDate//Время прогнозируемых данных, Unix, UTC

    var dayOfWeek: String = ""

    var nightTemp: Double//Ночная температура.

    var dayTemp: Double//Дневная температура.

    var humidity: Int//Влажность, %

    var pop: Int//Вероятность осадков

    var weatherDescription: String = ""

    var weatherIcon: String = ""

    init {
        date = LocalDateTime.ofEpochSecond((daily.dt + timezoneOffset).toLong(), 0, ZoneOffset.UTC).toLocalDate()
        nightTemp = daily.temp.night
        dayTemp = daily.temp.day
        humidity = daily.humidity
        pop = (daily.pop * 100).toInt()
        dayOfWeek = getDayOfWeek(date.dayOfWeek)
        if(daily.weather.isNotEmpty()) {
            weatherDescription = daily.weather[0].description
            weatherIcon = daily.weather[0].icon
        }
    }
}