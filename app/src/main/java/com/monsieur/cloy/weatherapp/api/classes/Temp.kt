package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Temp {
    @SerializedName("morn")
    @Expose
    var morn: Double? = null//Утренняя температура.

    @SerializedName("day")
    @Expose
    var day: Double? = null//Дневная температура.

    @SerializedName("eve")
    @Expose
    var eve: Double? = null// Вечерняя температура.

    @SerializedName("night")
    @Expose
    var night: Double? = null//Ночная температура.

    @SerializedName("min")
    @Expose
    var min: Double? = null//Минимальная дневная температура.

    @SerializedName("max")
    @Expose
    var max: Double? = null//Максимальная дневная температура.
}