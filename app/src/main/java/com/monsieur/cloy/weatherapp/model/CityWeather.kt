package com.monsieur.cloy.weatherapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.weatherapp.api.OneCallWeatherData
import com.monsieur.cloy.weatherapp.api.classes.Current
import com.monsieur.cloy.weatherapp.api.classes.Sys
import java.time.LocalDateTime
import java.time.ZoneOffset

class CityWeather(latitude: Double, longitude: Double, cityName: String, timezoneOffset: Int) {

    @ColumnInfo(name = "cityWeatherId")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "latitude")
    @NonNull
    var latitude: Double = latitude

    @ColumnInfo(name = "longitude")
    @NonNull
    var longitude: Double = longitude

    @ColumnInfo(name = "timezoneOffset")
    @NonNull
    var timezoneOffset: Int = timezoneOffset

    @ColumnInfo(name = "cityName")
    @NonNull
    var cityName: String = cityName

    @ColumnInfo(name = "lastUpdateTime")
    var lastUpdateTimeUTC: LocalDateTime? = null

    @ColumnInfo(name = "currentWeather")
    var currentWeather: CurrentWeather? = null

    @ColumnInfo(name = "hourlyWeather")
    var hourlyWeather: ArrayList<HourlyWeather> = ArrayList()

    @ColumnInfo(name = "dailyWeather")
    var dailyWeather: ArrayList<DailyWeather> = ArrayList()

    fun updateWeatherData(oneCallWeatherData: OneCallWeatherData){
        timezoneOffset = oneCallWeatherData.timezoneOffset
        lastUpdateTimeUTC = LocalDateTime.ofEpochSecond(oneCallWeatherData.current.dt.toLong(), 0, ZoneOffset.UTC)
        currentWeather = CurrentWeather(oneCallWeatherData.current, timezoneOffset)
        hourlyWeather.clear()
        dailyWeather.clear()
        for(hour in oneCallWeatherData.hourly){
            hourlyWeather.add(HourlyWeather(hour, timezoneOffset))
        }
        for (day in oneCallWeatherData.daily){
            dailyWeather.add(DailyWeather(day, timezoneOffset))
        }
    }
}