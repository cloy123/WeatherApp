package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Temp(
    @SerializedName("morn")
    @Expose
    var morn: Double,//Утренняя температура.
    @SerializedName("day")
    @Expose
    var day: Double,//Дневная температура.
    @SerializedName("eve")
    @Expose
    var eve: Double,// Вечерняя температура.
    @SerializedName("night")
    @Expose
    var night: Double,//Ночная температура.
    @SerializedName("min")
    @Expose
    var min: Double,//Минимальная дневная температура.
    @SerializedName("max")
    @Expose
    var max: Double//Максимальная дневная температура.
) {
}