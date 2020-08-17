package com.example.weatherapp.network

import com.example.weatherapp.model.Location
import com.example.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://www.metaweather.com/api/location/"

interface WeatherApi {
    @GET("search?")
    fun getLocation(@Query("query") location: String): Call<List<Location>>

    @GET("{woeid}")
    fun getWeather(@Path("woeid") woeid: Int): Call<WeatherResponse>
}