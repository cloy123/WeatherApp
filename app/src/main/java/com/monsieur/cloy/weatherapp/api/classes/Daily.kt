package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Daily(
    @SerializedName("dt")
    @Expose
    var dt: Int,//Время прогнозируемых данных, Unix, UTC
    @SerializedName("sunrise")
    @Expose
    var sunrise: Int,//Время восхода солнца, Unix, UT
    @SerializedName("sunset")
    @Expose
    var sunset: Int,//Время заката, Unix, UTC
    @SerializedName("moonrise")
    @Expose
    var moonrise: Int,//Время восхода луны в этот день, Unix, UTC
    @SerializedName("moonset")
    @Expose
    var moonset: Int,//Время захода луны для этого дня, Unix, UTC
    @SerializedName("moon_phase")
    @Expose
    var moonPhase: Double,//Фаза луны. 0и 1«новая луна», 0.25«первая четверть луны», 0.5«полная луна» и 0.75«последняя четверть луны». Периоды между ними называются «растущей серповидностью», «растущей луной», «убывающей луной» и «убывающей серповидностью» соответственно.
    @SerializedName("temp")
    @Expose
    var temp: Temp,
    @SerializedName("feels_like")
    @Expose
    var feelsLike: FeelsLike,//Это объясняет человеческое восприятие погоды.
    @SerializedName("pressure")
    @Expose
    var pressure: Int,//Атмосферное давление на уровне моря, гПа
    @SerializedName("humidity")
    @Expose
    var humidity: Int,//Влажность, %
    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double,//Атмосферная температура (зависит от давления и влажности), ниже которой начинают конденсироваться капли воды и может образовываться роса
    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double,//Скорость ветра
    @SerializedName("wind_gust")
    @Expose
    var windGust: Double,//(при наличии) Порыв ветра
    @SerializedName("wind_deg")
    @Expose
    var windDeg: Int,//Направление ветра, градусы (метеорологические)
    @SerializedName("clouds")
    @Expose
    var clouds: Int,//Облачность, %
    @SerializedName("uvi")
    @Expose
    var uvi: Double,//Максимальное значение УФ-индекса за сутки
    @SerializedName("pop")
    @Expose
    var pop: Double,//Вероятность осадков
    @SerializedName("rain")
    @Expose
    var rain: Double,//(при наличии) Объем осадков, мм
    @SerializedName("snow")
    @Expose
    var snow: Double,//(при наличии) Объем снега, мм
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>
) {
}