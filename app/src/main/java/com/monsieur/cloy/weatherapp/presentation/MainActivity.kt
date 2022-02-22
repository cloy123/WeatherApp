package com.monsieur.cloy.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monsieur.cloy.weatherapp.databinding.ActivityMainBinding
import com.monsieur.cloy.weatherapp.presentation.ui.mainFragment.MainFragment
import com.monsieur.cloy.weatherapp.presentation.utilits.APP_ACTIVITY
import com.monsieur.cloy.weatherapp.presentation.utilits.replaceFragment
import com.monsieur.cloy.weatherapp.presentation.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this

        replaceFragment(MainFragment(), false)
    }
}