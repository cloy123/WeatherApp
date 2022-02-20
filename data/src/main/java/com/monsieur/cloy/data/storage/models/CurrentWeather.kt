package com.monsieur.cloy.data.storage.models

import com.monsieur.cloy.data.api.models.Current
import java.time.*
import java.time.format.DateTimeFormatter

class CurrentWeather {

    val sunrise: LocalTime get() = LocalTime.parse(sunriseStr)//Время восхода солнца

    var sunriseStr: String

    val sunset: LocalTime get() = LocalTime.parse(sunsetStr)//Время заката

    var sunsetStr: String

    var temp: Double//Температура

    var feelsLikeTemp: Double

    var pressure: Int//Атмосферное давление на уровне моря, гПа

    var humidity: Int// Влажность, %

    var clouds: Int//Облачность, %

    var uvi: Double//Текущий УФ-индекс

    var visibility: Int//Средняя видимость, м

    var windSpeed: Double//Скорость ветра

    var weatherDescription: String = ""

    var weatherIcon: String = ""

    constructor(
        sunrise: LocalTime,
        sunset: LocalTime,
        temp: Double,
        feelsLikeTemp: Double,
        pressure: Int,
        humidity: Int,
        clouds: Int,
        uvi: Double,
        visibility: Int,
        windSpeed: Double,
        weatherDescription: String,
        weatherIcon: String
    ) {
        this.sunriseStr = sunrise.toString()
        this.sunsetStr = sunset.toString()
        this.temp = temp
        this.feelsLikeTemp = feelsLikeTemp
        this.pressure = pressure
        this.humidity = humidity
        this.clouds = clouds
        this.uvi = uvi
        this.visibility = visibility
        this.windSpeed = windSpeed
        this.weatherDescription = weatherDescription
        this.weatherIcon = weatherIcon
    }

    constructor(current: Current, timezoneOffset: Int) {
        sunriseStr = LocalDateTime.ofEpochSecond(
            (current.sunrise + timezoneOffset).toLong(),
            0,
            ZoneOffset.UTC
        ).toLocalTime().toString()
        sunsetStr = LocalDateTime.ofEpochSecond(
            (current.sunset + timezoneOffset).toLong(),
            0,
            ZoneOffset.UTC
        ).toLocalTime().toString()
        temp = current.temp
        feelsLikeTemp = current.feelsLike
        pressure = current.pressure
        humidity = current.humidity
        clouds = current.clouds
        uvi = current.uvi
        visibility = current.visibility
        windSpeed = current.windSpeed
        if (current.weather.isNotEmpty()) {
            weatherDescription = current.weather[0].description
            weatherIcon = current.weather[0].icon
        }
    }
}