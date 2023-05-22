package com.evildev.citytracker.ui.city.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evildev.citytracker.data.CityModel
import com.evildev.citytracker.databinding.CityItemBinding

class CityRecyclerViewAdapter(private val clickListener: (CityModel) -> Unit) :
    RecyclerView.Adapter<CityRecyclerViewHolder>() {
    private val cities = ArrayList<CityModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityRecyclerViewHolder {
        val binding = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CityRecyclerViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city, clickListener)
    }

    fun setData(cities: List<CityModel>) {
        this.cities.clear()
        this.cities.addAll(cities)
    }
}