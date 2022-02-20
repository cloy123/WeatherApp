package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.repository.CityWeatherRepository

class DeleteCityWeatherByIdUseCase(private val weatherRepository: CityWeatherRepository) {
    suspend fun execute(id: Int){
        weatherRepository.deleteCityWeatherById(id)
    }
}