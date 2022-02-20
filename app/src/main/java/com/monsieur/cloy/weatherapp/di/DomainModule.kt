package com.monsieur.cloy.weatherapp.di

import com.monsieur.cloy.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory<AddNewCityWeatherUseCase> {
        AddNewCityWeatherUseCase(weatherRepository = get())
    }
    factory<DeleteCityWeatherByIdUseCase> {
        DeleteCityWeatherByIdUseCase(weatherRepository = get())
    }
    factory<GetAllCityWeatherUseCase> {
        GetAllCityWeatherUseCase(weatherRepository = get())
    }
    factory<GetFavoriteCityIdUseCase> {
        GetFavoriteCityIdUseCase(favoriteCityIdRepository = get())
    }
    factory<SaveFavoriteCityIdUseCase> {
        SaveFavoriteCityIdUseCase(favoriteCityIdRepository = get())
    }
    factory<UpdateAllCityWeatherDataUseCase> {
        UpdateAllCityWeatherDataUseCase(weatherRepository = get())
    }
}