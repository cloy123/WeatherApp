package com.monsieur.cloy.weatherapp.model

data class City(var region: String,
                var city: String,
                var latitude: Double,
                var longitude: Double,
                var population: Int) {
}