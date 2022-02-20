package com.monsieur.cloy.weatherapp.presentation.ui.placeManagementFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import com.monsieur.cloy.domain.models.CityWeatherInfo
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.databinding.FragmentPlaceManagementBinding
import com.monsieur.cloy.weatherapp.presentation.ui.addCityFragment.AddCityFragment
import com.monsieur.cloy.weatherapp.presentation.ui.placeManagementFragment.adapters.CitiesRecyclerAdapter
import com.monsieur.cloy.weatherapp.presentation.utilits.*
import com.monsieur.cloy.weatherapp.presentation.viewModels.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaceManagementFragment : Fragment() {

    private lateinit var binding: FragmentPlaceManagementBinding

    private lateinit var recyclerAdapter: CitiesRecyclerAdapter

    private var cityWeatherInfo: List<CityWeatherInfo> = ArrayList()

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaceManagementBinding.inflate(layoutInflater)
        initFunc()
        return binding.root
    }

    private fun initRecyclerAdapter(){
        recyclerAdapter = CitiesRecyclerAdapter()
        binding.recyclerOtherCities.adapter = recyclerAdapter
        recyclerAdapter.setOnClickListener {
            currentCityId = it.id//viewModel.setCurrentCity(it.id)
            backButton()
        }
        recyclerAdapter.setOnDeleteCityListener {
            viewModel.deleteCity(it.id)
        }
        recyclerAdapter.setOnMakeFavoriteCityListener {
            favoriteCityId = it.id
            viewModel.saveFavoriteCity(it.id)
            setData()
        }
    }

    private fun initFunc(){
        initRecyclerAdapter()
        binding.toolbar.setNavigationOnClickListener {
            backButton()
        }
        binding.toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.action_add){
                replaceFragment(AddCityFragment())
            }
            true
        }
        binding.buttonDeleteFavoriteCity.setOnClickListener {
            viewModel.deleteCity(favoriteCityId)//(viewModel.favoriteCityId)
            favoriteCityId = -1
            viewModel.saveFavoriteCity(-1)
        }
        binding.favoriteCityCard.setOnClickListener {
            currentCityId = favoriteCityId
            //viewModel.setCurrentCity(viewModel.favoriteCityId)
            backButton()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setData(){
        //viewModelm.favoriteCityId
        val favoriteCity = cityWeatherInfo.find { it.id == favoriteCityId }
        val otherCities: List<CityWeatherInfo>
        if(favoriteCity != null){
            binding.tvFavoriteCityName.text = favoriteCity.cityName
            binding.tvFavoriteCityRegion.text = favoriteCity.region
            binding.tvFavoriteCityCurrentTemp.text = favoriteCity.currentWeather!!.temp.toInt().toString() + "°"
            binding.imageFavoriteCityWeather.setImageResource(getWeatherIconId(favoriteCity.currentWeather!!.weatherIcon))
            binding.tvFavoriteCityDayNightTemp.text = favoriteCity.dailyWeather[0].dayTemp.toInt().toString() + "°/" + favoriteCity.dailyWeather[0].nightTemp.toInt().toString() + "°"
            otherCities = cityWeatherInfo.filter { it.id != favoriteCityId }
        }else{
            otherCities = cityWeatherInfo
        }
        recyclerAdapter.setItems(otherCities)
    }

    override fun onStart() {
        super.onStart()
        lifecycle.coroutineScope.launch{
            viewModel.allCityWeatherInfo.collect {
                cityWeatherInfo = it
                setData()
            }
        }
    }
}