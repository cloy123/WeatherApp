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
import com.google.gson.Gson
import com.monsieur.cloy.weatherapp.api.CurrentWeatherData
import com.monsieur.cloy.weatherapp.api.OneCallWeatherData
import retrofit2.Response


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val units = "metric"
    private val lang = "ru"

    private lateinit var iWeather: IWeather

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

    override fun onStart() {
        super.onStart()
        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
            .serializeNulls().excludeFieldsWithModifiers(Modifier.FINAL,
                Modifier.TRANSIENT, Modifier.STATIC).create())).build()

        iWeather = retrofit.create(IWeather::class.java)



        binding.buttonCurrent.setOnClickListener {
            var callResult = iWeather.getCurrentWeatherData("набережные челны", apiKey, units, lang)

            callResult.enqueue(object : Callback<CurrentWeatherData?> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<CurrentWeatherData?>?,
                    response: Response<CurrentWeatherData?>
                ) {

                    if(response.isSuccessful){
                        try {
                            val gson = Gson()
                            val gsonStr = gson.toJson(response.body())
                            binding.info.setText(gsonStr + " " + "наб ч" ?:"нет ответа")
                        }
                        catch (e: Exception){
                            binding.info.setText(e.message + "|||||" + e.localizedMessage)
                        }
                    }else{
                        binding.info.setText("не Successful")
                    }
                }

                override fun onFailure(call: Call<CurrentWeatherData?>, t: Throwable) {
                    Log.e(TAG, "onFailure")
                    Log.e(TAG, t.toString())
                }
            })
        }

        binding.buttonOneCall.setOnClickListener {
            var callResult = iWeather.getOneCallWeatherData("55.76", "52.06", apiKey, units, lang)

            callResult.enqueue(object : Callback<OneCallWeatherData?> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<OneCallWeatherData?>?,
                    response: Response<OneCallWeatherData?>
                ) {

                    if(response.isSuccessful){
                        try {
                            val gson = Gson()
                            val gsonStr = gson.toJson(response.body())
                            binding.info.setText(gsonStr + " " + "елб" ?:"нет ответа")
                        }
                        catch (e: Exception){
                            binding.info.setText(e.message + "|||||" + e.localizedMessage)
                        }
                    }else{
                        binding.info.setText("не Successful")
                    }
                }

                override fun onFailure(call: Call<OneCallWeatherData?>, t: Throwable) {
                    Log.e(TAG, "onFailure")
                    Log.e(TAG, t.toString())
                }
            })
        }
    }


    interface IWeather{

        @GET("/data/2.5/weather")
        fun getCurrentWeatherData(@Query("q") city: String, @Query("appid") key: String, @Query("units") units: String, @Query("lang") lang: String): Call<CurrentWeatherData>

        @GET("/data/2.5/onecall")
        fun getOneCallWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") key: String, @Query("units") units: String, @Query("lang") lang: String): Call<OneCallWeatherData>
    }




}