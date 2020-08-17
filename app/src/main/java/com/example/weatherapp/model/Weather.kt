package com.example.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Weather(
    @PrimaryKey val id: Long,
    val the_temp: Double,
    val humidity: Float,
    val weather_state_abbr: String
)