package com.example.weatherapp.model


class WeatherResponse(
    val consolidated_weather: List<Weather>,
    val time: String,
    val title: String,
    val woeid: Int
)
