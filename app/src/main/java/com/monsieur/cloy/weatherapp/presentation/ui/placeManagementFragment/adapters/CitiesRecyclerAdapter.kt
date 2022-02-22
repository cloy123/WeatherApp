package com.monsieur.cloy.weatherapp.presentation.ui.placeManagementFragment.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.domain.models.CityWeatherInfo
import com.monsieur.cloy.weatherapp.presentation.utilits.getWeatherIconId

class CitiesRecyclerAdapter(): RecyclerView.Adapter<CitiesRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.place_management_city_card, parent, false)
        return ViewHolder(v)
    }

    private var items: List<CityWeatherInfo> = ArrayList()

    private var clickListener: ((cityWeatherInfo: CityWeatherInfo) -> Unit)? = null

    fun setOnClickListener(l: (cityWeatherInfo: CityWeatherInfo) -> Unit){
        clickListener = l
    }

    private var onDeleteCityListener: ((cityWeatherInfo: CityWeatherInfo) -> Unit)? = null

    fun setOnDeleteCityListener(l: (cityWeatherInfo: CityWeatherInfo) -> Unit){
        onDeleteCityListener = l
    }


    private var onMakeFavoriteCityListener: ((cityWeatherInfo: CityWeatherInfo) -> Unit)? = null

    fun setOnMakeFavoriteCityListener(l: (cityWeatherInfo: CityWeatherInfo) -> Unit){
        onMakeFavoriteCityListener = l
    }



    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<CityWeatherInfo>){
        this.items = items
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(items.isNotEmpty()){
            val cityWeather = items[position]
            holder.cityName.text = cityWeather.cityName
            holder.cityRegion.text = cityWeather.region
            if(cityWeather.currentWeather != null) {
                holder.cityCurrentTemp.text =
                    cityWeather.currentWeather!!.temp.toInt().toString() + "°"
                val icon = cityWeather.currentWeather!!.weatherIcon
                holder.cityWeatherIcon.setImageResource(getWeatherIconId(icon))
            }
            if(cityWeather.dailyWeather.isNotEmpty()){
                holder.cityDayNightTemp.text = cityWeather.dailyWeather[0].dayTemp.toInt()
                    .toString() + "°/" + cityWeather.dailyWeather[0].nightTemp.toInt()
                    .toString() + "°"
            }
            holder.card.setOnClickListener {
                clickListener?.invoke(cityWeather)
            }
            holder.deleteCity.setOnClickListener {
                onDeleteCityListener?.invoke(cityWeather)
            }
            holder.makeFavoriteCity.setOnClickListener {
                onMakeFavoriteCityListener?.invoke(cityWeather)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cityName: TextView = itemView.findViewById(R.id.tv_city_name)
        var cityCurrentTemp: TextView = itemView.findViewById(R.id.tv_city_current_temp)
        var cityRegion: TextView = itemView.findViewById(R.id.tv_city_region)
        var cityWeatherIcon: ImageView = itemView.findViewById(R.id.image_weather_icon)
        var cityDayNightTemp: TextView = itemView.findViewById(R.id.tv_city_day_night_temp)
        var card: CardView = itemView.findViewById(R.id.card)
        var deleteCity: MaterialButton = itemView.findViewById(R.id.button_delete_city)
        var makeFavoriteCity: MaterialButton = itemView.findViewById(R.id.button_make_favorite_city)
    }
}