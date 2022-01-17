package com.monsieur.cloy.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monsieur.cloy.weatherapp.databinding.ActivityMainBinding
import com.monsieur.cloy.weatherapp.utilits.APP_ACTIVITY
import com.monsieur.cloy.weatherapp.utilits.replaceFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this

        replaceFragment(MainFragment())
    }
}