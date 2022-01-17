package com.monsieur.cloy.weatherapp.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.weatherapp.api.classes.*

class CurrentWeatherData(
    @SerializedName("coord")
    @Expose
    var coord: Coord,
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>,
    @SerializedName("base")
    @Expose
    var base: String,//Внутренний параметр
    @SerializedName("main")
    @Expose
    var main: Main,
    @SerializedName("visibility")
    @Expose
    var visibility: Int,
    @SerializedName("wind")
    @Expose
    var wind: Wind,
    @SerializedName("rain")
    @Expose
    private val rain: Rain,
    @SerializedName("snow")
    @Expose
    private val snow: Snow,
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds,
    @SerializedName("dt")
    @Expose
    var dt: Int,//Время расчета данных, unix, UTC
    @SerializedName("sys")
    @Expose
    var sys: Sys,
    @SerializedName("timezone")
    @Expose
    var timezone: Int,//Сдвиг в секундах от UTC
    @SerializedName("id")
    @Expose
    private var id: Int,//Идентификатор города
    @SerializedName("name")
    @Expose
    var name: String,//Название города
    @SerializedName("cod")
    @Expose
    var cod: Int//Внутренний параметр
) {
}