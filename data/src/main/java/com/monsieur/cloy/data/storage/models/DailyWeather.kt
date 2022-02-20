package com.monsieur.cloy.data.storage.models

import com.monsieur.cloy.data.api.models.Daily
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class DailyWeather {

    val date: LocalDate get() = LocalDate.parse(dateStr)//Время прогнозируемых данных, Unix, UTC
    var dateStr: String


    var nightTemp: Double//Ночная температура.

    var dayTemp: Double//Дневная температура.

    var humidity: Int//Влажность, %

    var pop: Int//Вероятность осадков

    var weatherDescription: String = ""

    var weatherIcon: String = ""

    constructor(daily: Daily, timezoneOffset: Int) {
        dateStr = LocalDateTime.ofEpochSecond((daily.dt + timezoneOffset).toLong(), 0, ZoneOffset.UTC).toLocalDate().toString()
        nightTemp = daily.temp.night
        dayTemp = daily.temp.day
        humidity = daily.humidity
        pop = (daily.pop * 100).toInt()
        if(daily.weather.isNotEmpty()) {
            weatherDescription = daily.weather[0].description
            weatherIcon = daily.weather[0].icon
        }
    }

    constructor(date: LocalDate,
                nightTemp: Double,
                dayTemp: Double,
                humidity: Int,
                pop: Int,
                weatherDescription: String,
                weatherIcon: String){
        this.dateStr = date.toString()
        this.nightTemp = nightTemp
        this.dayTemp = dayTemp
        this.humidity = humidity
        this.pop = pop
        this.weatherDescription = weatherDescription
        this.weatherIcon = weatherIcon
    }
}