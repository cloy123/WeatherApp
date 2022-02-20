package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hourly(
    @SerializedName("dt")
    @Expose
    var dt: Int,//Время прогнозируемых данных, Unix, UTC
    @SerializedName("temp")
    @Expose
    var temp: Double,//Температура
    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double,//Температура. Это объясняет человеческое восприятие погоды
    @SerializedName("pressure")
    @Expose
    var pressure: Int,// Атмосферное давление на уровне моря, гПа
    @SerializedName("humidity")
    @Expose
    var humidity: Int,// Влажность, %
    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double,//Атмосферная температура (зависит от давления и влажности), ниже которой начинают конденсироваться капли воды и может образовываться роса
    @SerializedName("uvi")
    @Expose
    var uvi: Double,//УФ-индекс
    @SerializedName("clouds")
    @Expose
    var clouds: Int,// Облачность, %
    @SerializedName("visibility")
    @Expose
    var visibility: Int,//Средняя видимость, м
    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double,//Скорость ветра
    @SerializedName("wind_gust")
    @Expose
    var windGust: Double,//(при наличии) Порыв ветра
    @SerializedName("wind_deg")
    @Expose
    var windDeg: Int,//Направление ветра, градусы (метеорологические)
    @SerializedName("pop")
    @Expose
    var pop: Double,// Вероятность осадков
    @SerializedName("rain")
    @Expose
    var rain: Rain,
    @SerializedName("snow")
    @Expose
    var snow: Snow,
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>
) {
}