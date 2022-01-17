package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hourly{
    @SerializedName("dt")
    @Expose
    var dt: Int? = null//Время прогнозируемых данных, Unix, UTC

    @SerializedName("temp")
    @Expose
    var temp: Double? = null//Температура

    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double? = null//Температура. Это объясняет человеческое восприятие погоды

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null// Атмосферное давление на уровне моря, гПа

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null// Влажность, %

    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double? = null//Атмосферная температура (зависит от давления и влажности), ниже которой начинают конденсироваться капли воды и может образовываться роса

    @SerializedName("uvi")
    @Expose
    var uvi: Double? = null//УФ-индекс

    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null// Облачность, %

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null//Средняя видимость, м

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double? = null//Скорость ветра

    @SerializedName("wind_gust")
    @Expose
    var windGust: Double? = null//(при наличии) Порыв ветра

    @SerializedName("wind_deg")
    @Expose
    var windDeg: Int? = null//Направление ветра, градусы (метеорологические)

    @SerializedName("pop")
    @Expose
    var pop: Double? = null// Вероятность осадков

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null

    @SerializedName("snow")
    @Expose
    var snow: Snow? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null
}