package com.example.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherapp.model.Location
import com.example.weatherapp.repository.SearchActivityRepo

class SearchActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = SearchActivityRepo(application)
    val progress: LiveData<Boolean>
    val locationList: LiveData<List<Location>>

    init {
        this.progress = repo.progress
        this.locationList = repo.locationList
    }

    fun changeState() {
        repo.changeState()
    }

    fun searchLocation(location: String) {
        repo.searchLocation(location)
    }
}