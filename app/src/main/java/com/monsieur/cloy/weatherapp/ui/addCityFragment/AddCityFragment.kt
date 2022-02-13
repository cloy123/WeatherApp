package com.monsieur.cloy.weatherapp.ui.addCityFragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.databinding.FragmentAddCityBinding
import com.monsieur.cloy.weatherapp.model.City
import com.monsieur.cloy.weatherapp.ui.addCityFragment.adapters.CitiesRecyclerAdapter
import com.monsieur.cloy.weatherapp.utilits.backButton
import com.monsieur.cloy.weatherapp.utilits.getCitiesFromAsset
import com.monsieur.cloy.weatherapp.utilits.showToast
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.DividerItemDecoration
import android.graphics.drawable.InsetDrawable

import android.graphics.drawable.Drawable

import android.content.res.TypedArray
import androidx.fragment.app.viewModels
import com.monsieur.cloy.weatherapp.viewModels.MainViewModel


class AddCityFragment : Fragment() {

    private lateinit var binding: FragmentAddCityBinding
    private var cities: ArrayList<City> = ArrayList()
    private lateinit var recyclerAdapter: CitiesRecyclerAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCityBinding.inflate(layoutInflater)
        initFunc()
        return binding.root
    }

    private fun initRecycler(){
        val ATTRS = intArrayOf(android.R.attr.listDivider)

        val a = requireContext().obtainStyledAttributes(ATTRS)
        val divider = a.getDrawable(0)
        val inset = resources.getDimensionPixelSize(R.dimen.horizontal_margin)
        val insetDivider = InsetDrawable(divider, inset, 0, inset, 0)
        a.recycle()

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(insetDivider)

        binding.recyclerCities.addItemDecoration(itemDecoration)
        recyclerAdapter = CitiesRecyclerAdapter()
        binding.recyclerCities.adapter = recyclerAdapter
    }

    private fun initFunc(){
        initRecycler()
        binding.toolbar.setNavigationOnClickListener {
            backButton()
        }
        cities = getCitiesFromAsset()
        cities.sortBy { -it.population }

        recyclerAdapter.setOnClickListener {
            viewModel.createCityWeather(it)
            backButton()
        }
        recyclerAdapter.setItems(cities)
        val menuItem: MenuItem = binding.toolbar.menu.findItem(R.id.action_search)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText == null){
                    recyclerAdapter.setItems(cities)
                }else{
                    recyclerAdapter.setItems(cities.filter { it.city.lowercase().startsWith(newText.lowercase()) })
                }
                return true
            }
        })
    }
}