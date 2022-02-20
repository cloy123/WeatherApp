package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Alert(
    @SerializedName("sender_name")
    @Expose
    var senderName: String,//Имя источника оповещения.
    @SerializedName("event")
    @Expose
    var event: String,//Название события оповещения
    @SerializedName("start")
    @Expose
    var start: Int,// Дата и время начала оповещения, Unix, UTC
    @SerializedName("end")
    @Expose
    var end: Int,//Дата и время окончания оповещения, Unix, UTC
    @SerializedName("description")
    @Expose
    var description: String,// Описание оповещения
    @SerializedName("tags")
    @Expose
    var tags: List<String>//Тип суровой погоды
) {
}