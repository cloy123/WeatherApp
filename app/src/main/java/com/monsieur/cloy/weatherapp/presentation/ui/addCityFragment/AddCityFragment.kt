package com.monsieur.cloy.weatherapp.presentation.ui.addCityFragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.databinding.FragmentAddCityBinding
import com.monsieur.cloy.weatherapp.presentation.model.City
import com.monsieur.cloy.weatherapp.presentation.ui.addCityFragment.adapters.CitiesRecyclerAdapter
import com.monsieur.cloy.weatherapp.presentation.utilits.backButton
import com.monsieur.cloy.weatherapp.presentation.utilits.getCitiesFromAsset
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.DividerItemDecoration
import android.graphics.drawable.InsetDrawable
import com.monsieur.cloy.weatherapp.presentation.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddCityFragment : Fragment() {

    private lateinit var binding: FragmentAddCityBinding
    private var cities: ArrayList<City> = ArrayList()
    private lateinit var recyclerAdapter: CitiesRecyclerAdapter
    private val viewModel: MainViewModel by viewModel()

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
            viewModel.createCity(it)
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