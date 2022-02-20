package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Minutely(
    @SerializedName("dt")
    @Expose
    var dt: Int,//Время прогнозируемых данных, unix, UTC
    @SerializedName("precipitation")
    @Expose
    var precipitation: Double//Объем осадков, мм
) {

}