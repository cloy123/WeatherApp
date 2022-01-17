package com.monsieur.cloy.weatherapp.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.weatherapp.api.classes.*

class CurrentWeatherData {
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather?>? = null

    @SerializedName("base")
    @Expose
    var base: String? = null//Внутренний параметр

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("rain")
    @Expose
    private val rain: Rain? = null

    @SerializedName("snow")
    @Expose
    private val snow: Snow? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @SerializedName("dt")
    @Expose
    var dt: Int? = null//Время расчета данных, unix, UTC

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null

    @SerializedName("timezone")
    @Expose
    var timezone: Int? = null//Сдвиг в секундах от UTC

    @SerializedName("id")
    @Expose
    private var id: Int? = null//Идентификатор города

    @SerializedName("name")
    @Expose
    var name: String? = null//Название города

    @SerializedName("cod")
    @Expose
    var cod: Int? = null//Внутренний параметр
}