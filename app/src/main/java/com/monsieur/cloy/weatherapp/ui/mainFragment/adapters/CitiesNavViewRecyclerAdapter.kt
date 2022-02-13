package com.monsieur.cloy.weatherapp.ui.mainFragment.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.model.cityWeather.CityWeather
import com.monsieur.cloy.weatherapp.utilits.getWeatherIconId

class CitiesNavViewRecyclerAdapter(val items: List<CityWeather>): RecyclerView.Adapter<CitiesNavViewRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cities_navigation_view_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(items.isNotEmpty()){
            val cityWeather = items[position]
            holder.city.text = cityWeather.cityName
            holder.cityTemp.text = cityWeather.currentWeather?.temp.toString() + "°"
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
        var city: TextView = itemView.findViewById(R.id.tv_city)
        var cityTemp: TextView = itemView.findViewById(R.id.tv_chosen_city_temp)
        var cityWeatherIcon: ImageView = itemView.findViewById(R.id.image_city_weather)
    }
}