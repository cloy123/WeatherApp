package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.CityWeatherInfo
import com.monsieur.cloy.domain.repository.CityWeatherRepository

class GetAllCityWeatherUseCase(private val weatherRepository: CityWeatherRepository) {
    fun execute() = weatherRepository.getAllCityWeather()
}