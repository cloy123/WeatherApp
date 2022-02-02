package com.monsieur.cloy.weatherapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.model.DailyWeather
import com.monsieur.cloy.weatherapp.utilits.getPopIconId
import com.monsieur.cloy.weatherapp.utilits.getWeatherIconId
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DailyWeatherRecyclerAdapter(val items: List<DailyWeather>): RecyclerView.Adapter<DailyWeatherRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.daily_weather_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(items.isNotEmpty()){
            val weather = items[position]
            holder.pop.text = weather.pop.toString() + "%"
            holder.dayTemp.text = weather.dayTemp.toInt().toString() + "°"
            holder.nightTemp.text = weather.nightTemp.toInt().toString() + "°"
            holder.popIcon.setImageResource(getPopIconId(weather.pop))
            holder.weatherIcon.setImageResource(getWeatherIconId(weather.weatherIcon))
            if(position == 0){
                holder.dayOfWeek.text = "сегодня"
            }else{
                holder.dayOfWeek.text = weather.dayOfWeek
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dayOfWeek: TextView = itemView.findViewById(R.id.tv_day_of_week)
        var weatherIcon: ImageView = itemView.findViewById(R.id.image_weather_icon)
        var dayTemp: TextView = itemView.findViewById(R.id.tv_day_temp)
        var nightTemp: TextView = itemView.findViewById(R.id.tv_night_temp)
        var popIcon: ImageView = itemView.findViewById(R.id.image_pop_icon)
        var pop: TextView = itemView.findViewById(R.id.tv_pop)
    }
}