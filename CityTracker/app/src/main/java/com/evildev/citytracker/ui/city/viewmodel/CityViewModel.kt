package com.evildev.citytracker.ui.city.viewmodel

import android.text.Spannable.Factory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evildev.citytracker.CityReviewerApplication
import com.evildev.citytracker.data.CityModel
import com.evildev.citytracker.repositories.CityRepository

class CityViewModel(private val cityRepository: CityRepository) : ViewModel() {

    //    LiveData Values
    var name = MutableLiveData("")
    var population = MutableLiveData("")
    var status = MutableLiveData("")

    //    ViewModel Methods
    fun getCities() = cityRepository.getCities()
    fun addCity(city: CityModel) = cityRepository.addCity(city)

    fun createCity() {
        if (!validateData()) {
            status.value = WRONG_INFORMATION
            return
        }

        val city = CityModel(
            name.value!!,
            population.value!!
        )

        addCity(city)
        status.value = CITY_CREATED
    }

    //    Validate Input Data
    private fun validateData(): Boolean {
        when {
            name.value.isNullOrEmpty() -> return false
            population.value.isNullOrEmpty() -> return false
        }
        return true
    }

    //    Clear Input Data
    fun clearData() {
        name.value = ""
        population.value = ""
    }

    //    Clear App Status
    fun clearStatus() {
        status.value = INACTIVE
    }

    //    Setting Selected City Item
    fun setSelectedCity(city: CityModel) {
        name.value = city.name
        population.value = city.population
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as CityReviewerApplication
                CityViewModel(app.cityRepository)
            }
        }

        const val CITY_CREATED = "City created"
        const val WRONG_INFORMATION = "Wrong information"
        const val INACTIVE = "Inactive"
    }
}