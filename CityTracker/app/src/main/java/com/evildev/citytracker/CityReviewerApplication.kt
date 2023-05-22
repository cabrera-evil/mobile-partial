package com.evildev.citytracker

import android.app.Application
import com.evildev.citytracker.data.cities
import com.evildev.citytracker.repositories.CityRepository

class CityReviewerApplication: Application() {
    val cityRepository: CityRepository by lazy {
        CityRepository(cities)
    }
}