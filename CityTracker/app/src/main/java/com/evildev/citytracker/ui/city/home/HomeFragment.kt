package com.evildev.citytracker.ui.city.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.evildev.citytracker.R
import com.evildev.citytracker.data.CityModel
import com.evildev.citytracker.databinding.FragmentHomeBinding
import com.evildev.citytracker.ui.city.home.recyclerview.CityRecyclerViewAdapter
import com.evildev.citytracker.ui.city.viewmodel.CityViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CityRecyclerViewAdapter
    private val cityViewModel: CityViewModel by activityViewModels {
        CityViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun showSelectedItem(city: CityModel) {
        cityViewModel.setSelectedCity(city)
        view?.findNavController()?.navigate(R.id.action_homeFragment_to_cityFragment)
    }

    private fun displayCities() {
        adapter.setData(cityViewModel.getCities())
        adapter.notifyDataSetChanged()
    }

    private fun setRecyclerView(view: View) {
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter = CityRecyclerViewAdapter() { selectedCity ->
            showSelectedItem(selectedCity)
        }

        binding.recyclerView.adapter = adapter
        displayCities()
    }

    //    Adding fragment listeners
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView(view)

        binding.addCityButton.setOnClickListener {
            cityViewModel.clearData()
            it.findNavController().navigate(R.id.action_homeFragment_to_newCityFragment)
        }
    }
}