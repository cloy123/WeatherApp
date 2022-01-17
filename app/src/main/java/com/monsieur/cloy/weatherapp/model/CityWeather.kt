package com.monsieur.cloy.weatherapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class CityWeather(latitude: Double, longitude: Double, cityName: String) {

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

    @ColumnInfo(name = "cityName")
    @NonNull
    var cityName: String = cityName

    var lastUpdateTime: LocalDateTime? = null

}