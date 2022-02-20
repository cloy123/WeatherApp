package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.repository.CityWeatherRepository
import com.monsieur.cloy.domain.repository.FavoriteCityIdRepository

class SaveFavoriteCityIdUseCase(private val favoriteCityIdRepository: FavoriteCityIdRepository) {
    fun execute(id: Int){
        favoriteCityIdRepository.saveFavoriteCityId(id)
    }
}