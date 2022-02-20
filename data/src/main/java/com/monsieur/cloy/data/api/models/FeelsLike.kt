package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FeelsLike(
    @SerializedName("day")
    @Expose
    var day: Double,
    @SerializedName("night")
    @Expose
    var night: Double,
    @SerializedName("eve")
    @Expose
    var eve: Double,
    @SerializedName("morn")
    @Expose
    var morn: Double
) {
}