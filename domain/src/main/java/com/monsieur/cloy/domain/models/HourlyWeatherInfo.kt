package com.monsieur.cloy.domain.models

import java.time.LocalTime

class HourlyWeatherInfo(var time: LocalTime,
                        var temp: Double,
                        var humidity: Int,
                        var pop: Int,
                        var weatherDescription: String,
                        var weatherIcon: String) {
}