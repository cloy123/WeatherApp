package com.monsieur.cloy.weatherapp.di

import com.monsieur.cloy.weatherapp.presentation.viewModels.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(
            androidApplication(),
        addNewCityWeatherUseCase = get(),
        deleteCityWeatherByIdUseCase = get(),
        getAllCityWeatherUseCase = get(),
        getFavoriteCityIdUseCase = get(),
        saveFavoriteCityIdUseCase = get(),
        updateAllCityWeatherDataUseCase = get())
    }
}