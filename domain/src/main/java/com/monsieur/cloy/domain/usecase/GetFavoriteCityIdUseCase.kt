package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.repository.CityWeatherRepository
import com.monsieur.cloy.domain.repository.FavoriteCityIdRepository

class GetFavoriteCityIdUseCase(private val favoriteCityIdRepository: FavoriteCityIdRepository) {
    fun execute() : Int = favoriteCityIdRepository.getFavoriteCityId()
}