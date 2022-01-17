package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Minutely(
    @SerializedName("dt")
    @Expose
    var dt: Int,//Время прогнозируемых данных, unix, UTC
    @SerializedName("precipitation")
    @Expose
    var precipitation: Int//Объем осадков, мм
) {

}