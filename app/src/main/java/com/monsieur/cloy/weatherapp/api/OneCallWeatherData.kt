package com.monsieur.cloy.weatherapp.api

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.weatherapp.api.classes.*


class OneCallWeatherData {
    @SerializedName("lat")
    @Expose
    var lat: Double? = null//широта

    @SerializedName("lon")
    @Expose
    var lon: Double? = null//долгота

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null//Название часового пояса

    @SerializedName("timezone_offset")
    @Expose
    var timezoneOffset: Int? = null//Сдвиг в секундах от UTC

    @SerializedName("current")
    @Expose
    var current: Current? = null//Текущий ответ API данных о погоде

    @SerializedName("minutely")
    @Expose
    var minutely: List<Minutely>? = null//Ответ API данных минутного прогноза погоды

    @SerializedName("hourly")
    @Expose
    var hourly: List<Hourly>? = null//Почасовой ответ API данных о погоде

    @SerializedName("daily")
    @Expose
    var daily: List<Daily>? = null//Ежедневный ответ API данных прогноза погоды

    @SerializedName("alerts")
    @Expose
    var alerts: List<Alert>? = null//Данные национальных предупреждений о погоде из основных национальных систем предупреждения о погоде.
}