package com.evildev.citytracker.ui.city.home.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.evildev.citytracker.data.CityModel
import com.evildev.citytracker.databinding.CityItemBinding

class CityRecyclerViewHolder(private val binding: CityItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(city: CityModel, clickListener: (CityModel) -> Unit) {
        binding.nameTextView.text = city.name
        binding.populationTextView.text = city.population

        binding.cityItemCardView.setOnClickListener {
            clickListener(city)
        }
    }
}