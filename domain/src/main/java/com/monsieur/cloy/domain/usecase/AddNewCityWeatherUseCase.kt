package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.AddNewCityParam
import com.monsieur.cloy.domain.repository.CityWeatherRepository

class AddNewCityWeatherUseCase(private val weatherRepository: CityWeatherRepository) {
    suspend fun execute(addNewCityParam: AddNewCityParam){
        weatherRepository.addNewCityWeather(addNewCityParam)
    }
}