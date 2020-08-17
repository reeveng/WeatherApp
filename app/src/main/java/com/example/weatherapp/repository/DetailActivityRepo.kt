package com.example.weatherapp.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.BASE_URL
import com.example.weatherapp.network.WeatherApi
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivityRepo(var application: Application) {


    val progress = MutableLiveData<Boolean>()

    val response = MutableLiveData<WeatherResponse>()

    fun getWeather(woeid: Int) {
        progress.value = true
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(WeatherApi::class.java)

        val call = service.getWeather(woeid)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                progress.value = false
                Toast.makeText(application, "Error while getting data", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                res: Response<WeatherResponse>
            ) {
                progress.value = false
                response.value = res.body()
            }
        })

    }

}
