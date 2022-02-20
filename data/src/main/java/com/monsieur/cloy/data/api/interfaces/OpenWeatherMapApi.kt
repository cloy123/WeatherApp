package com.monsieur.cloy.data.api.interfaces

import com.monsieur.cloy.data.api.models.OneCallWeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {
    @GET("/data/2.5/onecall")
    fun getOneCallWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") key: String, @Query("units") units: String, @Query("lang") lang: String): Call<OneCallWeatherData>
}