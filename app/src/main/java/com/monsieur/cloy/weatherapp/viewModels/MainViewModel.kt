package com.monsieur.cloy.weatherapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.monsieur.cloy.weatherapp.appComponent
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val cityWeatherRepository = application.appComponent.cityWeatherRepository
    val allCityWeathers: LiveData<List<CityWeather>> = cityWeatherRepository.getAllCityWeather()

    fun addCityWeather(cityWeather: CityWeather){
        viewModelScope.launch(Dispatchers.IO) {
            cityWeatherRepository.insertCityWeather(cityWeather)
        }
    }

}