package com.example.weatherapp.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.model.Location
import com.example.weatherapp.model.Weather

@Database(entities = [Location::class, Weather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    companion object {
        fun get(application: Application): WeatherDatabase {
            return Room.databaseBuilder(application, WeatherDatabase::class.java, "weather")
                .build()
        }
    }

    abstract fun getWeatherDoa(): WeatherDao
}