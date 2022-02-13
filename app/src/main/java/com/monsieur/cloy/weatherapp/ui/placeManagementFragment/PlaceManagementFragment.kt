package com.monsieur.cloy.weatherapp.ui.placeManagementFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.monsieur.cloy.weatherapp.R
import com.monsieur.cloy.weatherapp.databinding.FragmentPlaceManagementBinding
import com.monsieur.cloy.weatherapp.ui.addCityFragment.AddCityFragment
import com.monsieur.cloy.weatherapp.utilits.backButton
import com.monsieur.cloy.weatherapp.utilits.replaceFragment

class PlaceManagementFragment : Fragment() {

    private lateinit var binding: FragmentPlaceManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaceManagementBinding.inflate(layoutInflater)
        initFunc()
        return binding.root
    }

    private fun initFunc(){
        binding.toolbar.setNavigationOnClickListener {
            backButton()
        }
        binding.toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.action_add){
                replaceFragment(AddCityFragment())
            }
            true
        }
    }
}