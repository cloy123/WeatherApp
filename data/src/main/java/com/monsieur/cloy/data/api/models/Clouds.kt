package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds(
    @SerializedName("all")
    @Expose
    var all: Int////Облачность, %
) {
}