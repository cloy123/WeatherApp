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
import android.util.Log
import android.widget.Toast
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


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val units = "metric"
    private val lang = "ru"

    private lateinit var openWeatherMapApi: OpenWeatherMapApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    fun initHourlyWeatherRecyclerAdapter(items: List<HourlyWeather>){
        val adapter = HourlyWeatherRecyclerAdapter(items)
        binding.recyclerHourlyWeather.adapter = adapter
    }

    fun initDailyWeatherRecyclerAdapter(items: List<DailyWeather>){
        val adapter = DailyWeatherRecyclerAdapter(items)
        binding.recyclerDailyWeather.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
            .serializeNulls().excludeFieldsWithModifiers(Modifier.FINAL,
                Modifier.TRANSIENT, Modifier.STATIC).create())).build()

        openWeatherMapApi = retrofit.create(OpenWeatherMapApi::class.java)



//
//        binding.buttonCurrent.setOnClickListener {
//            var callResult = openWeatherMapApi.getCurrentWeatherData("набережные челны", apiKey, units, lang)
//
//            callResult.enqueue(object : Callback<CurrentWeatherData?> {
//                @SuppressLint("SetTextI18n")
//                override fun onResponse(
//                    call: Call<CurrentWeatherData?>?,
//                    response: Response<CurrentWeatherData?>
//                ) {
//
//                    if(response.isSuccessful){
//                        try {
//                            val gson = Gson()
//                            val gsonStr = gson.toJson(response.body())
//                            binding.info.setText(gsonStr + " " + "наб ч" ?:"нет ответа")
//                        }
//                        catch (e: Exception){
//                            binding.info.setText(e.message + "|||||" + e.localizedMessage)
//                        }
//                    }else{
//                        binding.info.setText("не Successful")
//                    }
//                }
//
//                override fun onFailure(call: Call<CurrentWeatherData?>, t: Throwable) {
//                    Log.e(TAG, "onFailure")
//                    Log.e(TAG, t.toString())
//                }
//            })
//        }
//
            var callResult = openWeatherMapApi.getOneCallWeatherData("55.76", "52.02", apiKey, units, lang)

            callResult.enqueue(object : Callback<OneCallWeatherData?> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<OneCallWeatherData?>?,
                    response: Response<OneCallWeatherData?>
                ) {

                    if(response.isSuccessful){
                        val weather = CityWeather(0.0,0.0,"Елабуга",0)
                        if(response.body()!= null){
                            weather.updateWeatherData(response.body()!!)
                            initHourlyWeatherRecyclerAdapter(weather.hourlyWeather)
                            initDailyWeatherRecyclerAdapter(weather.dailyWeather)
                            binding.tvCityName.text = weather.cityName
                            binding.tvCurrentTemp.text = weather.currentWeather!!.temp!!.toInt().toString() + "°"
                            binding.tvFeelsLikeTemp.text = "Ощущается как " + weather.currentWeather!!.feelsLikeTemp.toInt().toString() + "°"
                            binding.tvDayNightTemp.text = weather.dailyWeather[0].dayTemp.toInt().toString() + "°/" + weather.dailyWeather[0].nightTemp.toInt().toString() + "°"
                            binding.imageCurrentWeatherIcon.setImageResource(getWeatherIconId(weather.currentWeather!!.weatherIcon))
                            binding.toolbar.title = weather.cityName
                        }else{
                            Toast.makeText(requireContext(), "error1", Toast.LENGTH_LONG).show()
                        }

                    }else{
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<OneCallWeatherData?>, t: Throwable) {
                    Log.e(TAG, "onFailure")
                    Log.e(TAG, t.toString())
                }
            })
    }
}