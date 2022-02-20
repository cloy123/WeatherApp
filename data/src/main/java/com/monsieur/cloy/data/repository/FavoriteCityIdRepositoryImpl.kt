package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.storage.FavoriteCityIdStorage
import com.monsieur.cloy.domain.repository.FavoriteCityIdRepository

class FavoriteCityIdRepositoryImpl(private val favoriteCityIdStorage: FavoriteCityIdStorage):
    FavoriteCityIdRepository {
    override fun getFavoriteCityId(): Int {
        return favoriteCityIdStorage.get()
    }

    override fun saveFavoriteCityId(id: Int) {
        favoriteCityIdStorage.save(id)
    }
}