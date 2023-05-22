package com.evildev.citytracker.ui.city.newcity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.evildev.citytracker.R
import com.evildev.citytracker.databinding.FragmentNewCityBinding
import com.evildev.citytracker.ui.city.viewmodel.CityViewModel

class NewCityFragment : Fragment() {
    lateinit var binding: FragmentNewCityBinding

    private val cityViewModel: CityViewModel by activityViewModels {
        CityViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        setObserver()
    }

    private fun setObserver() {
        binding.viewmodel = cityViewModel
    }

    private fun setViewModel() {
        cityViewModel.status.observe(viewLifecycleOwner) { status ->
            when {
                status.equals(CityViewModel.WRONG_INFORMATION) -> {
                    Log.d("APP_TAG", status)
                    cityViewModel.clearStatus()
                }
                status.equals(CityViewModel.CITY_CREATED) -> {
                    Log.d("APP_TAG", status)
                    Log.d("APP_TAG", cityViewModel.getCities().toString())
                    cityViewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }
}