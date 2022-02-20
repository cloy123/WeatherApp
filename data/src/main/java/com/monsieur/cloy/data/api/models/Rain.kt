package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rain(
    @SerializedName("1h")
    @Expose
    var _1h: Double,//Объем дождя за последний час, мм
    @SerializedName("3h")
    @Expose
    var _3h: Double//Объем дождя за последние 3 часа, мм
) {
}