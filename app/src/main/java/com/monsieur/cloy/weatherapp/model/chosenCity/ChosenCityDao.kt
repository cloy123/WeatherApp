package com.monsieur.cloy.weatherapp.model.chosenCity

import androidx.room.Dao
import androidx.room.Update
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeather

@Dao
interface ChosenCityDao {
    @Update
    suspend fun updateChosenCity(chosenCity: ChosenCity)
}