package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind(
    @SerializedName("speed")
    @Expose
    var speed: Double,//Скорость ветра
    @SerializedName("deg")
    @Expose
    var deg: Int,//Направление ветра, градусы (метеорологические)
    @SerializedName("gust")
    @Expose
    var gust: Double//Порыв ветра
) {
}