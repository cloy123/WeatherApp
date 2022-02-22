package com.monsieur.cloy.weatherapp.presentation.ui.mainFragment.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.domain.models.CityWeatherInfo
import com.monsieur.cloy.weatherapp.presentation.utilits.getWeatherIconId

class CitiesNavViewRecyclerAdapter(): RecyclerView.Adapter<CitiesNavViewRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cities_navigation_view_card, parent, false)
        return ViewHolder(v)
    }

    private var items: List<CityWeatherInfo> = ArrayList()

    private var clickListener: ((cityWeatherInfo: CityWeatherInfo) -> Unit)? = null

    fun setOnClickListener(l: (cityWeatherInfo: CityWeatherInfo) -> Unit){
        clickListener = l
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
            holder.city.text = cityWeather.cityName
            if(cityWeather.currentWeather != null) {
                holder.cityTemp.text = cityWeather.currentWeather?.temp?.toInt().toString() + "°"
                val icon = cityWeather.currentWeather?.weatherIcon
                if(icon != null){
                    holder.cityWeatherIcon.setImageResource(getWeatherIconId(icon))
                }
            }
            holder.card.setOnClickListener {
                clickListener?.invoke(cityWeather)
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var city: TextView = itemView.findViewById(R.id.tv_city)
        var cityTemp: TextView = itemView.findViewById(R.id.tv_city_temp)
        var cityWeatherIcon: ImageView = itemView.findViewById(R.id.image_city_weather)
        var card: ConstraintLayout = itemView.findViewById(R.id.card)
    }
}