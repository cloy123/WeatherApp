package com.monsieur.cloy.weatherapp.presentation.ui.mainFragment.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.domain.models.HourlyWeatherInfo
import com.monsieur.cloy.weatherapp.presentation.utilits.getPopIconId
import com.monsieur.cloy.weatherapp.presentation.utilits.getWeatherIconId
import java.time.format.DateTimeFormatter

class HourlyWeatherRecyclerAdapter(): RecyclerView.Adapter<HourlyWeatherRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.hourly_weather_card, parent, false)
        return ViewHolder(v)
    }

    private var items: List<HourlyWeatherInfo> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<HourlyWeatherInfo>){
        this.items = items
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(items.isNotEmpty()){
            val weather = items[position]
            holder.pop.text = weather.pop.toInt().toString() + "%"
            holder.temp.text = weather.temp.toInt().toString() + "°"
            holder.time.text = weather.time.format(DateTimeFormatter.ofPattern("HH:mm"))
            holder.popIcon.setImageResource(getPopIconId(weather.pop.toInt()))
            holder.weatherIcon.setImageResource(getWeatherIconId(weather.weatherIcon))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time: TextView = itemView.findViewById(R.id.tv_time)
        var weatherIcon: ImageView = itemView.findViewById(R.id.image_weather_icon)
        var temp: TextView = itemView.findViewById(R.id.tv_temp)
        var popIcon: ImageView = itemView.findViewById(R.id.image_pop_icon)
        var pop: TextView = itemView.findViewById(R.id.tv_pop)
    }
}