package com.example.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.model.Location

@Dao
interface WeatherDao {
    @Query("select * from Location where title like :search")
    fun getLocation(search: String): List<Location>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(list: List<Location>)
}