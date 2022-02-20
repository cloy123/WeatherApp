package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.monsieur.cloy.data.api.models.OneCallWeatherData
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity(tableName = "cityWeather")
class CityWeather {

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int

    @ColumnInfo(name = "latitude")
    @NonNull
    var latitude: Double

    @ColumnInfo(name = "longitude")
    @NonNull
    var longitude: Double

    @ColumnInfo(name = "timezoneOffset")
    @NonNull
    var timezoneOffset: Int

    @ColumnInfo(name = "cityName")
    @NonNull
    var cityName: String

    @ColumnInfo(name = "region")
    @NonNull
    var region: String

    @ColumnInfo(name = "lastUpdateTime")
    var lastUpdateTimeUTC: LocalDateTime?

    @ColumnInfo(name = "currentWeather")
    var currentWeather: CurrentWeather?

    @ColumnInfo(name = "hourlyWeather")
    var hourlyWeather: List<HourlyWeather>

    @ColumnInfo(name = "dailyWeather")
    var dailyWeather: List<DailyWeather>

    @Ignore
    constructor(latitude: Double, longitude: Double, cityName: String, region: String){
        this.latitude = latitude
        this.longitude = longitude
        this.cityName = cityName
        this.region = region
        this.hourlyWeather = ArrayList()
        this.dailyWeather = ArrayList()
        id = 0
        timezoneOffset = 0
        lastUpdateTimeUTC = null
        currentWeather = null
    }

    constructor(id: Int,
                latitude: Double,
                longitude: Double,
                timezoneOffset: Int,
                cityName: String,
                region: String,
                lastUpdateTimeUTC: LocalDateTime?,
                currentWeather: CurrentWeather?,
                hourlyWeather: List<HourlyWeather>,
                dailyWeather: List<DailyWeather>){
        this.id = id
        this.latitude = latitude
        this.longitude = longitude
        this.cityName = cityName
        this.region = region
        this.timezoneOffset = timezoneOffset
        this.lastUpdateTimeUTC = lastUpdateTimeUTC
        this.currentWeather = currentWeather
        this.hourlyWeather = hourlyWeather
        this.dailyWeather = dailyWeather
    }

    fun updateWeatherData(oneCallWeatherData: OneCallWeatherData){
        timezoneOffset = oneCallWeatherData.timezoneOffset
        lastUpdateTimeUTC = LocalDateTime.ofEpochSecond(oneCallWeatherData.current.dt.toLong(), 0, ZoneOffset.UTC)
        currentWeather = CurrentWeather(oneCallWeatherData.current, timezoneOffset)
        val hourList = ArrayList<HourlyWeather>()
        val dayList = ArrayList<DailyWeather>()
        for(hour in oneCallWeatherData.hourly){
            hourList.add(HourlyWeather(hour, timezoneOffset))
        }
        for (day in oneCallWeatherData.daily){
            dayList.add(DailyWeather(day, timezoneOffset))
        }
        hourlyWeather = hourList
        dailyWeather = dayList
    }
}