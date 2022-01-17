package com.monsieur.cloy.weatherapp.api.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Daily{
    @SerializedName("dt")
    @Expose
    var dt: Int? = null//Время прогнозируемых данных, Unix, UTC

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null//Время восхода солнца, Unix, UT

    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null//Время заката, Unix, UTC

    @SerializedName("moonrise")
    @Expose
    var moonrise: Int? = null//Время восхода луны в этот день, Unix, UTC

    @SerializedName("moonset")
    @Expose
    var moonset: Int? = null//Время захода луны для этого дня, Unix, UTC

    @SerializedName("moon_phase")
    @Expose
    var moonPhase: Double? = null//Фаза луны. 0и 1«новая луна», 0.25«первая четверть луны», 0.5«полная луна» и 0.75«последняя четверть луны». Периоды между ними называются «растущей серповидностью», «растущей луной», «убывающей луной» и «убывающей серповидностью» соответственно.

    @SerializedName("temp")
    @Expose
    var temp: Temp? = null

    @SerializedName("feels_like")
    @Expose
    var feelsLike: FeelsLike? = null//Это объясняет человеческое восприятие погоды.

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null//Атмосферное давление на уровне моря, гПа

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null//Влажность, %

    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double? = null//Атмосферная температура (зависит от давления и влажности), ниже которой начинают конденсироваться капли воды и может образовываться роса

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double? = null//Скорость ветра

    @SerializedName("wind_gust")
    @Expose
    var windGust: Double? = null//(при наличии) Порыв ветра

    @SerializedName("wind_deg")
    @Expose
    var windDeg: Int? = null//Направление ветра, градусы (метеорологические)

    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null//Облачность, %

    @SerializedName("uvi")
    @Expose
    var uvi: Double? = null//Максимальное значение УФ-индекса за сутки

    @SerializedName("pop")
    @Expose
    var pop: Double? = null//Вероятность осадков

    @SerializedName("rain")
    @Expose
    var rain: Double? = null//(при наличии) Объем осадков, мм

    @SerializedName("snow")
    @Expose
    var snow: Double? = null//(при наличии) Объем снега, мм

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null
}