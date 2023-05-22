package com.evildev.citytracker.repositories

import com.evildev.citytracker.data.CityModel

class CityRepository (private val cities: MutableList<CityModel>){
    fun getCities() = cities

    fun addCity(cityModel: CityModel){
        cities.add(cityModel)
    }
}