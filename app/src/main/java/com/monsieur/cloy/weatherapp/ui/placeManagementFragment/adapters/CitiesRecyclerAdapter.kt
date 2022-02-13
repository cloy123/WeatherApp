package com.monsieur.cloy.weatherapp.ui.placeManagementFragment.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeather
import com.monsieur.cloy.weatherapp.ui.mainFragment.adapters.CitiesNavViewRecyclerAdapter
import com.monsieur.cloy.weatherapp.utilits.getWeatherIconId

class CitiesRecyclerAdapter(val items: List<CityWeather>): RecyclerView.Adapter<CitiesRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.place_management_city_card, parent, false)
        return CitiesRecyclerAdapter.ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(items.isNotEmpty()){
            val cityWeather = items[position]
            holder.cityName.text = cityWeather.cityName
            holder.cityRegion.text = cityWeather.region
            holder.cityCurrentTemp.text = cityWeather.currentWeather?.temp.toString() + "°"
            holder.cityDayNightTemp.text = cityWeather.dailyWeather[0].dayTemp.toInt()
                .toString() + "°/" + cityWeather.dailyWeather[0].nightTemp.toInt()
                .toString() + "°"
            val icon = cityWeather.currentWeather?.weatherIcon
            if(icon != null){
                holder.cityWeatherIcon.setImageResource(getWeatherIconId(icon))
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
    }
}