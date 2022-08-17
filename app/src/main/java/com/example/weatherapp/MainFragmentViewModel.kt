package com.example.weatherapp

import androidx.lifecycle.*
import com.example.weatherapp.data.ApiRetrofit
import com.example.weatherapp.data.models.WeatherApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragmentViewModel : ViewModel() {


    private val _weatherApiState = MutableStateFlow<WeatherApiState>(WeatherApiState.Empty)
    val weatherApiState: StateFlow<WeatherApiState> = _weatherApiState

//    fun getCurrentWeatherIO() {
//
//        progressLiveData.value = true
//
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = ApiRetrofit.getApiClient().getCurrentWeather()
//            if (response.isSuccessful) {
//                resultWeatherLiveData.postValue(response.body())
//            }
//        }
//
//        progressLiveData.value = false
//    }

//    init {
//        getCurrentWeather2()
//    }

    fun getCurrentWeather2() {

        viewModelScope.launch(Dispatchers.Main) {

            _weatherApiState.value = WeatherApiState.Loading

            withContext(Dispatchers.IO) {
                try {
                    val weatherApiData = ApiRetrofit.getApiClient().getCurrentWeather()
                    _weatherApiState.value = WeatherApiState.Success(weatherApiData)
                } catch (e: Exception) {
                    _weatherApiState.value = WeatherApiState.Error(e.message.toString())
                }
            }
        }
    }

    fun getCurrentWeather3() {

        viewModelScope.launch(Dispatchers.Main) {

            _weatherApiState.value = WeatherApiState.Loading

            flow {
                emit(ApiRetrofit.getApiClient().getCurrentWeather())
            }.flowOn(Dispatchers.IO).catch { e ->
                _weatherApiState.value = WeatherApiState.Error(e.message.toString())
            }.collect { data ->
                _weatherApiState.value = WeatherApiState.Success(data)
            }
        }
    }
}