package com.monsieur.cloy.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import com.monsieur.cloy.weatherapp.databinding.FragmentMainBinding
import com.monsieur.cloy.weatherapp.utilits.apiKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.Modifier
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.res.Resources
import android.util.Log
import android.view.MenuItem
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.monsieur.cloy.weatherapp.api.CurrentWeatherData
import com.monsieur.cloy.weatherapp.api.OneCallWeatherData
import com.monsieur.cloy.weatherapp.api.OpenWeatherMapApi
import com.monsieur.cloy.weatherapp.model.DailyWeather
import com.monsieur.cloy.weatherapp.model.HourlyWeather
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeather
import com.monsieur.cloy.weatherapp.ui.DailyWeatherRecyclerAdapter
import com.monsieur.cloy.weatherapp.ui.HourlyWeatherRecyclerAdapter
import com.monsieur.cloy.weatherapp.utilits.getWeatherIconId
import retrofit2.Response
import java.time.format.DateTimeFormatter


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val units = "metric"
    private val lang = "ru"

    private lateinit var tvChosenCity: TextView
    private lateinit var tvChosenCityTemp: TextView
    private lateinit var imageChosenCityWeather: ImageView
    private lateinit var navView: View
    private lateinit var recyclerCities: RecyclerView
    private lateinit var buttonManagePlaces: MaterialButton

    private lateinit var openWeatherMapApi: OpenWeatherMapApi

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

    private fun initFunc() {
        navView = binding.navView.getHeaderView(0)
        tvChosenCity = navView.findViewById(R.id.tv_chosen_city)
        tvChosenCityTemp = navView.findViewById(R.id.tv_chosen_city_temp)
        imageChosenCityWeather = navView.findViewById(R.id.image_chosen_city_weather)
        recyclerCities = navView.findViewById(R.id.recycler_cities)
        buttonManagePlaces = navView.findViewById(R.id.button_manage_places)
        binding.toolbar.setNavigationOnClickListener {
            binding.motionLayout.open()
        }
    }

    fun initHourlyWeatherRecyclerAdapter(items: List<HourlyWeather>) {
        val adapter = HourlyWeatherRecyclerAdapter(items)
        binding.recyclerHourlyWeather.adapter = adapter
    }

    fun initDailyWeatherRecyclerAdapter(items: List<DailyWeather>) {
        val adapter = DailyWeatherRecyclerAdapter(items)
        binding.recyclerDailyWeather.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .serializeNulls().excludeFieldsWithModifiers(
                            Modifier.FINAL,
                            Modifier.TRANSIENT, Modifier.STATIC
                        ).create()
                )
            ).build()

        openWeatherMapApi = retrofit.create(OpenWeatherMapApi::class.java)

        val callResult =
            openWeatherMapApi.getOneCallWeatherData("55.76", "52.02", apiKey, units, lang)

        callResult.enqueue(object : Callback<OneCallWeatherData?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<OneCallWeatherData?>?,
                response: Response<OneCallWeatherData?>
            ) {

                if (response.isSuccessful) {
                    val weather = CityWeather(0.0, 0.0, "Елабуга", 0)
                    if (response.body() != null) {
                        weather.updateWeatherData(response.body()!!)
                        initHourlyWeatherRecyclerAdapter(weather.hourlyWeather)
                        initDailyWeatherRecyclerAdapter(weather.dailyWeather)
                        binding.tvCityName.text = weather.cityName
                        binding.tvCurrentTemp.text =
                            weather.currentWeather!!.temp.toInt().toString() + "°"
                        binding.tvFeelsLikeTemp.text =
                            "Ощущается как " + weather.currentWeather!!.feelsLikeTemp.toInt()
                                .toString() + "°"
                        binding.tvDayNightTemp.text = weather.dailyWeather[0].dayTemp.toInt()
                            .toString() + "°/" + weather.dailyWeather[0].nightTemp.toInt()
                            .toString() + "°"
                        binding.imageCurrentWeatherIcon.setImageResource(getWeatherIconId(weather.currentWeather!!.weatherIcon))
                        binding.toolbar.title = weather.cityName
                        binding.tvSunrise.text =
                            weather.currentWeather!!.sunrise.format(DateTimeFormatter.ofPattern("HH:mm"))
                        binding.tvSunset.text =
                            weather.currentWeather!!.sunset.format(DateTimeFormatter.ofPattern("HH:mm"))
                        binding.tvUvi.text = weather.currentWeather!!.uvi.toInt().toString()
                        binding.tvWindSpeed.text =
                            weather.currentWeather!!.windSpeed.toInt().toString() + "м/c"
                        binding.tvHumidity.text = weather.currentWeather!!.humidity.toString() + "%"
                        tvChosenCity.text = weather.cityName
                        tvChosenCityTemp.text = weather.currentWeather!!.temp.toInt().toString() + "°"
                        imageChosenCityWeather.setImageResource(getWeatherIconId(weather.currentWeather!!.weatherIcon))
                    }
                }
            }
            override fun onFailure(call: Call<OneCallWeatherData?>, t: Throwable) {
                Log.e(TAG, "onFailure")
                Log.e(TAG, t.toString())
                Toast.makeText(requireContext(), "ошибка", Toast.LENGTH_LONG).show()
            }
        })
    }
}