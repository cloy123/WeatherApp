package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {
    @SerializedName("temp")
    @Expose
    var temp: Double? = null//Температура

    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double? = null//Температура. Этот температурный параметр определяет человеческое восприятие погоды

    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null//Минимальная температура на данный момент.

    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null//Максимальная температура на данный момент

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null//Атмосферное давление

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null// Влажность, %

    @SerializedName("sea_level")
    @Expose
    var seaLevel: Int? = null//Атмосферное давление на уровне моря, гПа

    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Int? = null//Атмосферное давление на уровне земли, гПа
}