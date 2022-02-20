package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coord(
    @SerializedName("lon")
    @Expose
    var lon: Double,//долгота
    @SerializedName("lat")
    @Expose
    var lat: Double//широта
) {
}