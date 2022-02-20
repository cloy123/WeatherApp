package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main(
    @SerializedName("temp")
    @Expose
    var temp: Double,//Температура
    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double,//Температура. Этот температурный параметр определяет человеческое восприятие погоды
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double,//Минимальная температура на данный момент.
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double,//Максимальная температура на данный момент
    @SerializedName("pressure")
    @Expose
    var pressure: Int,//Атмосферное давление
    @SerializedName("humidity")
    @Expose
    var humidity: Int,// Влажность, %
    @SerializedName("sea_level")
    @Expose
    var seaLevel: Int,//Атмосферное давление на уровне моря, гПа
    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Int//Атмосферное давление на уровне земли, гПа
) {
}