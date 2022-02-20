package com.monsieur.cloy.data.api

import com.google.gson.GsonBuilder
import com.monsieur.cloy.data.api.interfaces.OpenWeatherMapApi
import com.monsieur.cloy.data.api.models.OneCallWeatherData
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier

class WeatherApi(private val apiKey: String, private val units: String, private val lang: String) {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .serializeNulls().excludeFieldsWithModifiers(
                        Modifier.FINAL,
                        Modifier.TRANSIENT, Modifier.STATIC
                    ).create()
            )
        ).build()

    fun requestWeatherData(latitude:Double, longitude:Double): Response<OneCallWeatherData> {
        val openWeatherMapApi = retrofit.create(OpenWeatherMapApi::class.java)
        val response = openWeatherMapApi.getOneCallWeatherData(
            latitude.toString(),
            longitude.toString(),
            apiKey,
            units,
            lang
        )
        return response.execute()
    }
}