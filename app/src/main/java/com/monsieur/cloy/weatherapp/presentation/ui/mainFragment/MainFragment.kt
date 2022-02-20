package com.monsieur.cloy.weatherapp.presentation.ui.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monsieur.cloy.weatherapp.databinding.FragmentMainBinding
import android.annotation.SuppressLint
import android.content.res.Resources
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.monsieur.cloy.domain.models.CityWeatherInfo
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.presentation.ui.mainFragment.adapters.CitiesNavViewRecyclerAdapter
import com.monsieur.cloy.weatherapp.presentation.ui.mainFragment.adapters.DailyWeatherRecyclerAdapter
import com.monsieur.cloy.weatherapp.presentation.ui.mainFragment.adapters.HourlyWeatherRecyclerAdapter
import com.monsieur.cloy.weatherapp.presentation.ui.placeManagementFragment.PlaceManagementFragment
import com.monsieur.cloy.weatherapp.presentation.utilits.*
import com.monsieur.cloy.weatherapp.presentation.viewModels.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.format.DateTimeFormatter


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private var cityWeatherInfo: List<CityWeatherInfo> = ArrayList()

    private lateinit var buttonFavoriteCity: ConstraintLayout
    private lateinit var favoriteCityCard: ConstraintLayout
    private lateinit var tvFavoriteCity: TextView
    private lateinit var tvFavoriteCityTemp: TextView
    private lateinit var imageFavoriteCityWeather: ImageView
    private lateinit var buttonOtherCity: ConstraintLayout
    private lateinit var recyclerCities: RecyclerView
    private lateinit var buttonManagePlaces: MaterialButton

    private lateinit var hourlyWeatherRecyclerAdapter: HourlyWeatherRecyclerAdapter
    private lateinit var dailyWeatherRecyclerAdapter: DailyWeatherRecyclerAdapter
    private lateinit var citiesNavViewRecyclerAdapter: CitiesNavViewRecyclerAdapter

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        val widthOfNav = (Resources.getSystem().displayMetrics.widthPixels) * 0.8
        binding.navView.layoutParams.width = widthOfNav.toInt()
        binding.navView.requestLayout()
        binding.drawerContent.getConstraintSet(R.id.end)?.setMargin(R.id.content, ConstraintSet.START, widthOfNav.toInt())
        initFunc()
        return binding.root
    }

    private fun initNavView(){
        val navView = binding.navView.getHeaderView(0)
        buttonFavoriteCity = navView.findViewById(R.id.button_favorite_city)
        favoriteCityCard = navView.findViewById(R.id.favorite_city_card)
        tvFavoriteCity = navView.findViewById(R.id.tv_favorite_city)
        tvFavoriteCityTemp = navView.findViewById(R.id.tv_favorite_city_temp)
        imageFavoriteCityWeather = navView.findViewById(R.id.image_favorite_city_weather)
        buttonOtherCity = navView.findViewById(R.id.button_other_city)
        recyclerCities = navView.findViewById(R.id.recycler_cities)
        initCitiesNavViewRecyclerAdapter()
        buttonManagePlaces = navView.findViewById(R.id.button_manage_places)
    }

    private fun initFunc() {
        initNavView()
        initDailyWeatherRecyclerAdapter()
        initHourlyWeatherRecyclerAdapter()
        binding.toolbar.setNavigationOnClickListener {
            binding.motionLayout.open()
        }
        buttonFavoriteCity.setOnClickListener {
            currentCityId = favoriteCityId
            setWeatherData()
            binding.motionLayout.close()
        }
        favoriteCityCard.setOnClickListener {
            currentCityId = favoriteCityId
            setWeatherData()
            binding.motionLayout.close()
        }
        buttonOtherCity.setOnClickListener {
            replaceFragment(PlaceManagementFragment())
        }
        buttonManagePlaces.setOnClickListener {
            replaceFragment(PlaceManagementFragment())
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateWeatherData()
        }


        lifecycle.coroutineScope.launch {
            viewModel.allCityWeatherInfo.collect {
                cityWeatherInfo = it
                setWeatherData()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

    }


    fun initHourlyWeatherRecyclerAdapter() {
        hourlyWeatherRecyclerAdapter = HourlyWeatherRecyclerAdapter()
        binding.recyclerHourlyWeather.adapter = hourlyWeatherRecyclerAdapter
    }

    fun initDailyWeatherRecyclerAdapter() {
        dailyWeatherRecyclerAdapter = DailyWeatherRecyclerAdapter()
        binding.recyclerDailyWeather.adapter = dailyWeatherRecyclerAdapter
    }

    fun initCitiesNavViewRecyclerAdapter(){
        citiesNavViewRecyclerAdapter = CitiesNavViewRecyclerAdapter()
        recyclerCities.adapter = citiesNavViewRecyclerAdapter
        citiesNavViewRecyclerAdapter.setOnClickListener {
            currentCityId = it.id//viewModel.setCurrentCity(it.id)
            setWeatherData()
            binding.motionLayout.close()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setCurrentCityData(cityWeatherInfo: CityWeatherInfo){
        //TODO проверки
        dailyWeatherRecyclerAdapter.setItems(cityWeatherInfo.dailyWeather)
        hourlyWeatherRecyclerAdapter.setItems(cityWeatherInfo.hourlyWeather)
        binding.tvCityName.text = cityWeatherInfo.cityName
        binding.tvCurrentTemp.text = cityWeatherInfo.currentWeather!!.temp.toInt().toString() + "°"
        binding.tvFeelsLikeTemp.text = "Ощущается как " + cityWeatherInfo.currentWeather!!.feelsLikeTemp.toInt().toString() + "°"
        binding.tvDayNightTemp.text = cityWeatherInfo.dailyWeather[0].dayTemp.toInt().toString() + "°/" + cityWeatherInfo.dailyWeather[0].nightTemp.toInt().toString() + "°"
        binding.imageCurrentWeatherIcon.setImageResource(getWeatherIconId(cityWeatherInfo.currentWeather!!.weatherIcon))
        binding.toolbar.title = cityWeatherInfo.cityName
        binding.tvSunrise.text =
            cityWeatherInfo.currentWeather!!.sunrise.format(DateTimeFormatter.ofPattern("HH:mm"))
        binding.tvSunset.text =
            cityWeatherInfo.currentWeather!!.sunset.format(DateTimeFormatter.ofPattern("HH:mm"))
        binding.tvUvi.text = cityWeatherInfo.currentWeather!!.uvi.toInt().toString()
        binding.tvWindSpeed.text =
            cityWeatherInfo.currentWeather!!.windSpeed.toInt().toString() + "м/c"
        binding.tvHumidity.text = cityWeatherInfo.currentWeather!!.humidity.toString() + "%"
    }

    @SuppressLint("SetTextI18n")
    fun setWeatherData(){
        if(cityWeatherInfo.isNotEmpty()) {
            //viewModel.currentCityId
            //viewModel.favoriteCityId
            var currentCity = cityWeatherInfo.find { it.id == currentCityId }
            val favoriteCity = cityWeatherInfo.find { it.id == favoriteCityId }
            if(currentCity != null){
                setCurrentCityData(currentCity)
            }else if(favoriteCity != null){
                currentCityId = favoriteCityId
                //viewModel.setCurrentCity(favoriteCityId)
                currentCityId = favoriteCityId
                setCurrentCityData(favoriteCity)
            }else{
                currentCity = cityWeatherInfo[0]
                currentCityId = currentCity.id
                //viewModel.setCurrentCity(currentCityId)
                setCurrentCityData(currentCity)
            }

            val otherCities: List<CityWeatherInfo>
            if(favoriteCity != null){
                tvFavoriteCity.text = favoriteCity.cityName
                tvFavoriteCityTemp.text = favoriteCity.currentWeather!!.temp.toInt().toString() + "°"
                imageFavoriteCityWeather.setImageResource(getWeatherIconId(favoriteCity.currentWeather!!.weatherIcon))
                otherCities = cityWeatherInfo.filter { it.id != favoriteCityId }
            }else{
                otherCities = cityWeatherInfo
            }
            citiesNavViewRecyclerAdapter.setItems(otherCities)
        }else{
            //replaceFragment(AddCityFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        binding.motionLayout.close()
    }
}