package com.example.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.models.WeatherApiData

class MainFragment : Fragment() {

    private lateinit var vm: MainFragmentViewModel

    private lateinit var progressBar: ProgressBar
    private lateinit var cityName: TextView
    private lateinit var countryName: TextView
    private lateinit var temp: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm = ViewModelProvider(requireActivity())[MainFragmentViewModel::class.java]

        vm.getResultProgressData.observe(requireActivity()) {
            progressBar.isVisible = it
        }

//        vm.getCurrentWeatherIO()
        vm.getCurrentWeatherMain()

        vm.getResultWeatherData.observe(requireActivity(), ::showResult)
//        Toast.makeText(requireContext(), vm.getResultWeatherData.value.toString(), Toast.LENGTH_LONG).show()

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        cityName = view?.findViewById(R.id.city_name)!!
        countryName = view?.findViewById(R.id.country_name)!!
        temp = view?.findViewById(R.id.temp)!!
        progressBar = view?.findViewById(R.id.progress_bar)!!
    }

    private fun showResult(weatherApiData: WeatherApiData) {
        bindData(weatherApiData = weatherApiData)
    }

    private fun bindData(weatherApiData: WeatherApiData) {
        cityName.text = weatherApiData.location.name
        countryName.text = weatherApiData.location.country
        temp.text = weatherApiData.current.temp_c.toString()
    }
}