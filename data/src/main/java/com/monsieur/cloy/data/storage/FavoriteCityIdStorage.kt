package com.monsieur.cloy.data.storage

import android.content.Context

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_FAVORITE_CITY_ID = "favoriteCityId"
private const val DEFAULT_ID = -1


class FavoriteCityIdStorage(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun get(): Int{
        return sharedPreferences.getInt(KEY_FAVORITE_CITY_ID, DEFAULT_ID)
    }

    fun save(id: Int){
        sharedPreferences.edit().putInt(KEY_FAVORITE_CITY_ID, id).apply()
    }
}