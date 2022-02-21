package com.monsieur.cloy.weatherapp.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.monsieur.cloy.domain.models.AddNewCityParam
import com.monsieur.cloy.domain.models.CityWeatherInfo
import com.monsieur.cloy.weatherapp.presentation.model.City
import com.monsieur.cloy.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
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

    var currentCityId: Int = -1
    var favoriteCityId: Int = -1


    val allCityWeatherInfo = getAllCityWeatherUseCase.execute()

    override fun onCleared() {
        super.onCleared()
    }

    val favoriteCity: MutableLiveData<CityWeatherInfo?> = MutableLiveData()
    val currentCity: MutableLiveData<CityWeatherInfo> = MutableLiveData()

    val otherCities: MutableLiveData<List<CityWeatherInfo>> = MutableLiveData()

    var cityWeatherInfo: List<CityWeatherInfo> = ArrayList()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            favoriteCityId = loadFavoriteCityId()
            currentCityId = favoriteCityId

            allCityWeatherInfo.collect {
                if(it.isEmpty()){
                    return@collect
                }
                cityWeatherInfo = it
                val favCity = it.find { it.id ==  favoriteCityId}
                val curCity = it.find { it.id == currentCityId }
                if(favCity != null){
                    favoriteCity.postValue(favCity)
                }else{
                    favoriteCity.postValue(null)
                }
                if(curCity != null){
                    currentCity.postValue(curCity!!)
                }else{
                    if(favCity != null){
                        currentCityId = favoriteCityId
                        currentCity.postValue(favCity!!)
                    }else{
                        currentCityId = it[0].id
                        currentCity.postValue(it[0])
                    }
                }

                if(favCity != null){
                    otherCities.postValue(cityWeatherInfo.filter { it.id != favoriteCityId })
                }else{
                    otherCities.postValue(it)
                }
            }

        }

    }

    fun setFavoriteCity(id: Int){
        val favCity = cityWeatherInfo.find { it.id == id }
        if(favCity != null){
            favoriteCityId = id
            saveFavoriteCity(id)
            favoriteCity.postValue(favCity!!)
            otherCities.postValue(cityWeatherInfo.filter { it.id != favoriteCityId })
        }
    }

    fun setCurrentCity(id: Int){
        val curCity = cityWeatherInfo.find { it.id == id }
        if(curCity != null){
            currentCityId = id
            currentCity.postValue(curCity!!)
        }
    }

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