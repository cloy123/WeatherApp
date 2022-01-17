package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coord {
    @SerializedName("lon")
    @Expose
    var lon: Double? = null//долгота

    @SerializedName("lat")
    @Expose
    var lat: Double? = null//широта
}