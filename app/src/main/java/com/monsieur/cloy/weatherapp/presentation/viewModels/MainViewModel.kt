package com.monsieur.cloy.weatherapp.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monsieur.cloy.domain.models.AddNewCityParam
import com.monsieur.cloy.weatherapp.presentation.model.City
import com.monsieur.cloy.domain.usecase.*
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val addNewCityWeatherUseCase: AddNewCityWeatherUseCase,
    private val deleteCityWeatherByIdUseCase: DeleteCityWeatherByIdUseCase,
    private val getAllCityWeatherUseCase: GetAllCityWeatherUseCase,
    private val getFavoriteCityIdUseCase: GetFavoriteCityIdUseCase,
    private val saveFavoriteCityIdUseCase: SaveFavoriteCityIdUseCase,
    private val updateAllCityWeatherDataUseCase: UpdateAllCityWeatherDataUseCase
) : AndroidViewModel(application) {

//    var currentCityId: Int = -1
//        private set
//
//    var favoriteCityId: Int = -1
//        private set
//
//    init {
//        favoriteCityId = loadFavoriteCityId()
//        currentCityId = -1
//    }

    val allCityWeatherInfo = getAllCityWeatherUseCase.execute()

//    fun setCurrentCity(id: Int){
//        currentCityId = id
//    }

//    fun setFavoriteCity(id: Int){
//        //favoriteCityId = id
//        saveFavoriteCity(id)
//    }

    fun createCity(city: City) {
        viewModelScope.launch {
            val addNewCityParam =
                AddNewCityParam(city.latitude, city.longitude, city.city, city.region)
            addNewCityWeatherUseCase.execute(addNewCityParam)
        }
    }

    fun deleteCity(cityId: Int){
        viewModelScope.launch {
            deleteCityWeatherByIdUseCase.execute(cityId)
        }
    }

    fun saveFavoriteCity(cityId: Int){
        saveFavoriteCityIdUseCase.execute(cityId)
    }

    fun loadFavoriteCityId(): Int{
        return getFavoriteCityIdUseCase.execute()
    }

    fun updateWeatherData(){
        viewModelScope.launch {
            updateAllCityWeatherDataUseCase.execute()
        }
    }

}