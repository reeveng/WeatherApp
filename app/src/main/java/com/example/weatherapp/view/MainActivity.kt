package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.adapter.LocationSearchAdapter
import com.example.weatherapp.database.WeatherDatabase
import com.example.weatherapp.viewmodel.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchActivityViewModel

    private lateinit var adapter: LocationSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WeatherDatabase.get(application)
        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)

        ivSearch.setOnClickListener {
            viewModel.searchLocation(etSearch.text.toString())
        }

        viewModel.progress.observe(this, Observer {
            if (it) {
                pgSearch.visibility = View.VISIBLE
            } else {
                pgSearch.visibility = View.GONE
            }
        })

        viewModel.locationList.observe(this, Observer {
            adapter.setLocation(it)
        })
        adapter = LocationSearchAdapter(this)
        rvSearch.adapter = adapter


    }
}
