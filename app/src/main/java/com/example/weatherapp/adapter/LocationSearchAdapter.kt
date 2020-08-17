package com.example.weatherapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.Location
import com.example.weatherapp.view.DetailActivity
import kotlinx.android.synthetic.main.location_info.view.*

class LocationSearchAdapter(private var context: Context) :
    RecyclerView.Adapter<LocationSearchAdapter.SearchViewHolder>() {

    private var list: List<Location> = ArrayList()

    fun setLocation(list: List<Location>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.location_info, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currLocation = list[position]
        holder.itemView.tv_locationName.text = currLocation.title
        holder.itemView.tv_latlong.text = currLocation.latt_long
        holder.itemView.root_view.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("Location", currLocation.woeid)
            intent.putExtra("name", currLocation.title)
            context.startActivity(intent)
        }
    }
}