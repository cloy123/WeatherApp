package com.monsieur.cloy.weatherapp.ui.addCityFragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.databinding.FragmentAddCityBinding
import com.monsieur.cloy.weatherapp.utilits.backButton
import java.util.*

class AddCityFragment : Fragment() {

    private lateinit var binding: FragmentAddCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCityBinding.inflate(layoutInflater)
        initFunc()
        return binding.root
    }

    private fun initFunc(){
        binding.toolbar.setNavigationOnClickListener {
            backButton()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val menuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //TODO изменять список
                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }
}