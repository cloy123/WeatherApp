package com.monsieur.cloy.weatherapp.model

import com.monsieur.cloy.weatherapp.api.classes.Current
import java.time.*

class CurrentWeather(current: Current, timezoneOffset: Int) {

    var sunrise: LocalTime//Время восхода солнца

    var sunset: LocalTime//Время заката

    var temp: Double? = null//Температура

    var feelsLikeTemp: Double

    var pressure: Int//Атмосферное давление на уровне моря, гПа

    var humidity: Int// Влажность, %

    var clouds: Int//Облачность, %

    var uvi: Double//Текущий УФ-индекс

    var visibility: Int//Средняя видимость, м

    var windSpeed: Double//Скорость ветра

    var weatherDescription: String = ""

    var weatherIcon: String = ""

    init {
        sunrise = LocalDateTime.ofEpochSecond((current.sunrise + timezoneOffset).toLong(), 0, ZoneOffset.UTC).toLocalTime()
        sunset = LocalDateTime.ofEpochSecond((current.sunset + timezoneOffset).toLong(), 0, ZoneOffset.UTC).toLocalTime()
        temp = current.temp
        feelsLikeTemp = current.feelsLike
        pressure = current.pressure
        humidity = current.humidity
        clouds = current.clouds
        uvi = current.uvi
        visibility = current.visibility
        windSpeed = current.windSpeed
        if(current.weather.isNotEmpty()) {
            weatherDescription = current.weather[0].description
            weatherIcon = current.weather[0].icon
        }
    }
}