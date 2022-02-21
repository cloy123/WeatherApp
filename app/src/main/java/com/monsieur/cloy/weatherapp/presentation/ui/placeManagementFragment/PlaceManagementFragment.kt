package com.monsieur.cloy.weatherapp.presentation.ui.placeManagementFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaceManagementFragment : Fragment() {

    private lateinit var binding: FragmentPlaceManagementBinding

    private lateinit var recyclerAdapter: CitiesRecyclerAdapter

    private val viewModel: MainViewModel by sharedViewModel()

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
            viewModel.setCurrentCity(it.id)
            backButton()
        }
        recyclerAdapter.setOnDeleteCityListener {
            viewModel.deleteCity(it.id)
        }
        recyclerAdapter.setOnMakeFavoriteCityListener {
            viewModel.setFavoriteCity(it.id)
        }
    }

    @SuppressLint("SetTextI18n")
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
            viewModel.deleteCity(viewModel.favoriteCityId)//(viewModel.favoriteCityId)
            viewModel.saveFavoriteCity(-1)
        }
        binding.favoriteCityCard.setOnClickListener {
            viewModel.setCurrentCity(viewModel.favoriteCityId)
            backButton()
        }

        viewModel.favoriteCity.observe(viewLifecycleOwner, Observer {
            if(it != null){
                binding.favoriteCityCard.visibility = View.VISIBLE
                binding.tvFavoriteCityName.text = it.cityName
                binding.tvFavoriteCityRegion.text = it.region
                binding.tvFavoriteCityCurrentTemp.text = it.currentWeather!!.temp.toInt().toString() + "°"
                binding.imageFavoriteCityWeather.setImageResource(getWeatherIconId(it.currentWeather!!.weatherIcon))
                binding.tvFavoriteCityDayNightTemp.text = it.dailyWeather[0].dayTemp.toInt().toString() + "°/" + it.dailyWeather[0].nightTemp.toInt().toString() + "°"
            }else{
                binding.favoriteCityCard.visibility = View.GONE
            }
        })


        viewModel.otherCities.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setItems(it)
        })
    }
}