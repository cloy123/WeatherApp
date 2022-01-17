package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rain {
    @SerializedName("1h")
    @Expose
    var _1h: Double? = null//Объем дождя за последний час, мм

    @SerializedName("3h")
    @Expose
    var _3h: Double? = null//Объем дождя за последние 3 часа, мм
}