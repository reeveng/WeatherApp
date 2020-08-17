package com.example.weatherapp.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.Location
import com.example.weatherapp.network.BASE_URL
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.database.WeatherDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepo(var application: Application) {

    val progress = MutableLiveData<Boolean>()

    val locationList = MutableLiveData<List<Location>>()

    fun changeState() {
        progress.value = !(progress.value != null && progress.value!!)
    }

    fun searchLocation(location: String) {

        progress.value = true

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val weatherapi = retrofit.create(WeatherApi::class.java)

        val call = weatherapi.getLocation(location)
        call.enqueue(object : Callback<List<Location>> {
            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                progress.value = false
                Toast.makeText(application, "Error while getting data", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                progress.value = false
                insert(response.body()!!)
                locationList.value = response.body()
            }
        })
    }

    fun insert(list: List<Location>) = CoroutineScope(Dispatchers.Main).launch {
        WeatherDatabase.get(application).getWeatherDoa().insertLocation(list)
    }
}

