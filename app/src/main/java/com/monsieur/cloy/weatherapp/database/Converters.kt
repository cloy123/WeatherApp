package com.monsieur.cloy.weatherapp.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.monsieur.cloy.weatherapp.model.CurrentWeather
import com.monsieur.cloy.weatherapp.model.DailyWeather
import com.monsieur.cloy.weatherapp.model.HourlyWeather
import java.time.LocalDateTime

class Converters {
    @TypeConverter
    fun currentWeatherToJson(value: CurrentWeather):String = Gson().toJson(value)

    @TypeConverter
    fun jsonToCurrentWeather(value: String): CurrentWeather?{
        return try {
            Gson().fromJson(value, CurrentWeather::class.java)
        }catch (e: Exception){
            return null
        }
    }

    @TypeConverter
    fun listHourlyWeatherToJson(value: ArrayList<HourlyWeather>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToListHourlyWeather(value: String): ArrayList<HourlyWeather>{
        return try {
            Gson().fromJson(value, Array<HourlyWeather>::class.java).toList() as ArrayList<HourlyWeather>
        }catch (e: Exception){
            ArrayList()
        }
    }

    @TypeConverter
    fun listDailyWeatherToJson(value: ArrayList<DailyWeather>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToListDailyWeather(value: String): ArrayList<DailyWeather>{
        return try {
            Gson().fromJson(value, Array<DailyWeather>::class.java).toList() as ArrayList<DailyWeather>
        }catch (e: Exception){
            ArrayList()
        }
    }

    @TypeConverter
    fun localDateTimeToJson(value: LocalDateTime): String{
        return value.toString()
    }

    @TypeConverter
    fun jsonToLocalDateTime(value: String): LocalDateTime?{
        return try {
            LocalDateTime.parse(value)
        }catch (e: Exception){
            null
        }
    }
}