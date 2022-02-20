package com.monsieur.cloy.domain.repository

interface FavoriteCityIdRepository {
    fun getFavoriteCityId(): Int

    fun saveFavoriteCityId(id: Int)
}