package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("id")
    @Expose
    var id: Int? = null//Идентификатор погодных условий - https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2

    @SerializedName("main")
    @Expose
    var main: String? = null//Группа погодных параметров

    @SerializedName("description")
    @Expose
    var description: String? = null//Погодные условия в группе - https://openweathermap.org/api/one-call-api#multi

    @SerializedName("icon")
    @Expose
    var icon: String? = null//Идентификатор значка погоды - как получить значки - https://openweathermap.org/weather-conditions#How-to-get-icon-URL
}