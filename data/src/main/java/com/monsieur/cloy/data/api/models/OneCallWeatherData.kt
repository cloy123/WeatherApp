package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class OneCallWeatherData(
    @SerializedName("lat")
    @Expose
    var lat: Double,//широта
    @SerializedName("lon")
    @Expose
    var lon: Double,//долгота
    @SerializedName("timezone")
    @Expose
    var timezone: String,//Название часового пояса
    @SerializedName("timezone_offset")
    @Expose
    var timezoneOffset: Int,//Сдвиг в секундах от UTC
    @SerializedName("current")
    @Expose
    var current: com.monsieur.cloy.data.api.models.Current,//Текущий ответ API данных о погоде
    @SerializedName("minutely")
    @Expose
    var minutely: List<com.monsieur.cloy.data.api.models.Minutely>,//Ответ API данных минутного прогноза погоды
    @SerializedName("hourly")
    @Expose
    var hourly: List<com.monsieur.cloy.data.api.models.Hourly>,//Почасовой ответ API данных о погоде
    @SerializedName("daily")
    @Expose
    var daily: List<com.monsieur.cloy.data.api.models.Daily>,//Ежедневный ответ API данных прогноза погоды
    @SerializedName("alerts")
    @Expose
    var alerts: List<com.monsieur.cloy.data.api.models.Alert>//Данные национальных предупреждений о погоде из основных национальных систем предупреждения о погоде.
) {
}