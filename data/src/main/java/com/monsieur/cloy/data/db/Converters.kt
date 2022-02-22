package com.monsieur.cloy.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.monsieur.cloy.data.storage.models.CurrentWeather
import com.monsieur.cloy.data.storage.models.DailyWeather
import com.monsieur.cloy.data.storage.models.HourlyWeather
import java.time.LocalDateTime

class Converters {
    @TypeConverter
    fun currentWeatherToJson(value: CurrentWeather?):String = Gson().toJson(value)

    @TypeConverter
    fun jsonToCurrentWeather(value: String): CurrentWeather?{
        return try {
            Gson().fromJson(value, CurrentWeather::class.java)
        }catch (e: Exception){
            return null
        }
    }

    @TypeConverter
    fun listHourlyWeatherToJson(value: List<HourlyWeather>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToListHourlyWeather(value: String): List<HourlyWeather>{
        return try {
            Gson().fromJson(value, Array<HourlyWeather>::class.java).toList()
        }catch (e: Exception){
            ArrayList()
        }
    }

    @TypeConverter
    fun listDailyWeatherToJson(value: List<DailyWeather>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToListDailyWeather(value: String): List<DailyWeather>{
        return try {
            Gson().fromJson(value, Array<DailyWeather>::class.java).toList()
        }catch (e: Exception){
            ArrayList()
        }
    }

    @TypeConverter
    fun localDateTimeToJson(value: LocalDateTime?): String{
        if(value == null){
            return "null"
        }
        return value.toString()
    }

    @TypeConverter
    fun jsonToLocalDateTime(value: String): LocalDateTime?{
        if(value == "null"){
            return null
        }
        return try {
            LocalDateTime.parse(value)
        }catch (e: Exception){
            null
        }
    }
}