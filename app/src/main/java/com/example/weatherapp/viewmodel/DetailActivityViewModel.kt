package com.example.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repository.DetailActivityRepo

class DetailActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DetailActivityRepo(application)
    val progress: LiveData<Boolean>
    val response: LiveData<WeatherResponse>

    init {
        this.progress = repository.progress
        this.response = repository.response
    }

    fun getWeather(woeid: Int) {
        repository.getWeather(woeid)
    }
}