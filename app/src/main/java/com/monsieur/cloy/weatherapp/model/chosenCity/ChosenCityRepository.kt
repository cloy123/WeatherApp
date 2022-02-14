package com.monsieur.cloy.weatherapp.model.chosenCity

import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeatherDao
import javax.inject.Inject

class ChosenCityRepository @Inject constructor(val chosenCityDao: ChosenCityDao) {
}