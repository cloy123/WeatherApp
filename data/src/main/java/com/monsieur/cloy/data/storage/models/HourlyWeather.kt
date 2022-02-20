package com.monsieur.cloy.data.storage.models

import com.monsieur.cloy.data.api.models.Hourly
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

class HourlyWeather {

    val time: LocalTime get() = LocalTime.parse(timeStr)//Время прогнозируемых данных, Unix, UTC

    var timeStr: String

    var temp: Double//Температура

    var humidity: Int// Влажность, %

    var pop: Int// Вероятность осадков, %

    var weatherDescription: String = ""

    var weatherIcon: String = ""

    constructor(hourly: Hourly, timezoneOffset: Int) {
        timeStr = LocalDateTime.ofEpochSecond((hourly.dt + timezoneOffset).toLong(), 0, ZoneOffset.UTC).toLocalTime().toString()
        temp = hourly.temp
        humidity = hourly.humidity
        pop = (hourly.pop * 100).toInt()
        if(hourly.weather.isNotEmpty()) {
            weatherDescription = hourly.weather[0].description
            weatherIcon = hourly.weather[0].icon
        }
    }

    constructor(time: LocalTime, temp: Double, humidity: Int, pop: Int, weatherDescription: String, weatherIcon: String){
        this.timeStr = time.toString()
        this.temp = temp
        this.humidity = humidity
        this.pop = pop
        this.weatherDescription = weatherDescription
        this.weatherIcon = weatherIcon
    }
}