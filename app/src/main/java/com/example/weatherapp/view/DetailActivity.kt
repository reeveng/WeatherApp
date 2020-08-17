package com.example.weatherapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.viewmodel.DetailActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {


    private lateinit var viewModel: DetailActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewModel = ViewModelProvider(this).get(DetailActivityViewModel::class.java)


        if (intent.hasExtra("name")) {
            tv_location.text = intent.getStringExtra("name")
        }
        if (intent.hasExtra("Location")) {
            val location = intent.getIntExtra("Location", 0)
            viewModel.getWeather(location)
        }

        viewModel.progress.observe(this, Observer {
            if (it) {
                pbDetails.visibility = View.VISIBLE
            } else {
                pbDetails.visibility = View.GONE
            }
        })

        viewModel.response.observe(this, Observer {
            if (it != null) {
                tv_temp.text = "${it.consolidated_weather[0].the_temp.toInt()}"
                val format = SimpleDateFormat("dd/M/yyyy")
                tv_date_time.text = format.format(Date())
                val idDrawable: Int = when (it.consolidated_weather[0].weather_state_abbr) {
                    "c" -> R.drawable.ic_c
                    "h" -> R.drawable.ic_h
                    "hc" -> R.drawable.ic_hc
                    "hr" -> R.drawable.ic_hr
                    "lc" -> R.drawable.ic_lc
                    "lr" -> R.drawable.ic_lr
                    "s" -> R.drawable.ic_s
                    "sl" -> R.drawable.ic_sl
                    "sn" -> R.drawable.ic_sn
                    "t" -> R.drawable.ic_t
                    else -> R.drawable.ic_c
                }
                iv_weather.setImageResource(idDrawable)
            }
        })
    }
}