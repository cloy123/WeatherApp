package com.monsieur.cloy.weatherapp.viewModels

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.monsieur.cloy.weatherapp.api.OneCallWeatherData
import com.monsieur.cloy.weatherapp.api.OpenWeatherMapApi
import com.monsieur.cloy.weatherapp.appComponent
import com.monsieur.cloy.weatherapp.model.City
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeather
import com.monsieur.cloy.weatherapp.utilits.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val cityWeatherRepository = application.appComponent.cityWeatherRepository
    val allCityWeathers: LiveData<List<CityWeather>> = cityWeatherRepository.getAllCityWeather()
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

    fun createCityWeather(city: City) {
        val cityWeather = CityWeather(city.latitude, city.longitude, city.city, city.region)
        val callback = object :Callback<OneCallWeatherData?>{
            override fun onResponse(
                call: Call<OneCallWeatherData?>,
                response: Response<OneCallWeatherData?>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        cityWeather.updateWeatherData(response.body()!!)
                        insertCityWeather(cityWeather)
                    }
                }
            }
            override fun onFailure(call: Call<OneCallWeatherData?>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure")
                Log.e(ContentValues.TAG, t.toString())
            }
        }
        requestWeatherData(cityWeather.latitude, cityWeather.longitude, callback)
    }


    private fun insertCityWeather(cityWeather: CityWeather) {
        viewModelScope.launch(Dispatchers.IO) {
            cityWeatherRepository.insertCityWeather(cityWeather)
            viewModelScope.launch(Dispatchers.Main) {
            }
        }
    }

    private fun updateCityWeather(cityWeather: CityWeather){
        viewModelScope.launch(Dispatchers.IO) {
            cityWeatherRepository.updateCityWeather(cityWeather)
        }
    }

    private fun requestWeatherData(latitude:Double, longitude:Double, callback: Callback<OneCallWeatherData?>) {
        val openWeatherMapApi = retrofit.create(OpenWeatherMapApi::class.java)
        val callResult = openWeatherMapApi.getOneCallWeatherData(
            latitude.toString(),
            longitude.toString(),
            apiKey,
            units,
            lang
        )
        callResult.enqueue(callback)
    }
}