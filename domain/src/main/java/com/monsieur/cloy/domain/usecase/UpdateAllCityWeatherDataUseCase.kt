package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.repository.CityWeatherRepository
import java.time.LocalDateTime

class UpdateAllCityWeatherDataUseCase(private val weatherRepository: CityWeatherRepository) {
    suspend fun execute(){
        weatherRepository.updateAllWeatherData()
    }
}