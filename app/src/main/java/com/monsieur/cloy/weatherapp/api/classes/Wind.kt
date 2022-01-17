package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind {
    @SerializedName("speed")
    @Expose
    var speed: Double? = null//Скорость ветра

    @SerializedName("deg")
    @Expose
    var deg: Int? = null//Направление ветра, градусы (метеорологические)

    @SerializedName("gust")
    @Expose
    var gust: Double? = null//Порыв ветра
}