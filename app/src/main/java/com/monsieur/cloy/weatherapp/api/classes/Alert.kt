package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Alert{
    @SerializedName("sender_name")
    @Expose
    var senderName: String? = null//Имя источника оповещения.

    @SerializedName("event")
    @Expose
    var event: String? = null//Название события оповещения

    @SerializedName("start")
    @Expose
    var start: Int? = null// Дата и время начала оповещения, Unix, UTC

    @SerializedName("end")
    @Expose
    var end: Int? = null//Дата и время окончания оповещения, Unix, UTC

    @SerializedName("description")
    @Expose
    var description: String? = null// Описание оповещения

    @SerializedName("tags")
    @Expose
    var tags: List<String>? = null//Тип суровой погоды
}