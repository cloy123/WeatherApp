package com.monsieur.cloy.weatherapp.ui.addCityFragment.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.model.City

class CitiesRecyclerAdapter(): RecyclerView.Adapter<CitiesRecyclerAdapter.ViewHolder>() {


    private var clickListener: ((city: City) -> Unit)? = null

    fun setOnClickListener(l: (city: City) -> Unit){
        clickListener = l
    }

    private var items: List<City>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<City>){
        this.items = items
        notifyDataSetChanged()
    }

//    inline fun setOnClickListener(crossinline action: () -> Unit): CityClickListener{
//        return object: CityClickListener{
//            override fun onClick(city: City) {
//                action()
//            }
//
//        }
////        setOnClickListener(object : CityClickListener{
////            override fun onClick(city: City) {
////                TODO("Not yet implemented")
////            }
////
////        })
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.city_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!items.isNullOrEmpty()){
            val city = items!![position]
            holder.city.text = city.city
            holder.region.text = city.region
            holder.card.setOnClickListener {
                clickListener?.invoke(city)
            }
        }
    }

    override fun getItemCount(): Int {
        return if(items == null){
            0
        }else {
            items!!.size
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var city: TextView = itemView.findViewById(R.id.tv_city)
        var region: TextView = itemView.findViewById(R.id.tv_region)
        var card: ConstraintLayout = itemView.findViewById(R.id.card)
    }

    interface CityClickListener{
        fun onClick(city: City)
    }
}