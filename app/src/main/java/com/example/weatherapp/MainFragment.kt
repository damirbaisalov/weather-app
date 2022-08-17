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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.data.models.WeatherApiData
import com.example.weatherapp.data.models.WeatherApiState

class MainFragment : Fragment() {

//    private val mainRepository by lazy(LazyThreadSafetyMode.NONE) {MainRepository(apiRetrofit = ApiRetrofit)}
    private val vm: MainViewModel by viewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var cityName: TextView
    private lateinit var countryName: TextView
    private lateinit var temp: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm.getCurrentWeather2()

        lifecycleScope.launchWhenStarted {
            vm.weatherApiState.collect {
                when(it) {
                    is WeatherApiState.Success -> {
                        bindData(it.data)
                        progressBar.isVisible = false
                    }
                    is WeatherApiState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            it.msg,
                            Toast.LENGTH_LONG
                        ).show()
                        progressBar.isVisible = false
                    }
                    is WeatherApiState.Loading -> {
                        progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }

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

    private fun bindData(weatherApiData: WeatherApiData) {
        cityName.text = weatherApiData.location.name
        countryName.text = weatherApiData.location.country
        temp.text = weatherApiData.current.temp_c.toString()
    }
}