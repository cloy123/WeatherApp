package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Snow(
    @SerializedName("1h")
    @Expose
    var _1h: Double,//Объем снега за последний час, мм
    @SerializedName("3h")
    @Expose
    var _3h: Double//Объем снега за последние 3 часа, мм
) {

}