package com.example.weatherapp.presentation.screens.second_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.weatherapp.data.repository.CurrentWeatherRepositoryImpl
import com.example.weatherapp.domain.models.WeatherLocation
import com.example.weatherapp.presentation.NavRoutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondScreenViewModel(
    private val navController: NavHostController
): ViewModel() {

//    private val store = Store(
//        initialState = CurrentWeatherViewState(),
//        reducer = CurrentWeatherReducer()
//    )
    private val currentWeatherRepository = CurrentWeatherRepositoryImpl()
    private val reducer = CurrentWeatherReducer()

    private val _viewState =  MutableStateFlow(CurrentWeatherViewState())
    val viewState: StateFlow<CurrentWeatherViewState> = _viewState

    fun performAction(action: CurrentWeatherAction) {
        when(action) {
            is CurrentWeatherAction.CurrentWeatherLoading -> {
                _viewState.value = reducer.reduce(_viewState.value, action)
            }
            is CurrentWeatherAction.CurrentWeatherLoaded -> {
                _viewState.value = reducer.reduce(_viewState.value, action)
            }
            is CurrentWeatherAction.CurrentWeatherFailed -> {
                _viewState.value = reducer.reduce(_viewState.value, action)
            }
            is CurrentWeatherAction.CurrentWeatherCityNameChanged -> {
                getCurrentWeather(cityName = action.cityName)
            }
            is CurrentWeatherAction.GetForecastButtonClicked -> {
                navController.navigate(NavRoutes.ThirdScreen.route + "/${action.cityName}")
            }
            is CurrentWeatherAction.RefreshButtonClicked -> {
                navController.popBackStack()
            }
        }
    }

    fun getCurrentWeather(cityName: String) = viewModelScope.launch {

        _viewState.value = reducer.reduce(
            _viewState.value,
            CurrentWeatherAction.CurrentWeatherLoading
        )

        withContext(Dispatchers.IO) {
            try {
                val weatherData = currentWeatherRepository.getCurrentWeather(
                    WeatherLocation(
                        cityName = cityName,
                        days = ""
                    )
                )
                _viewState.value = reducer.reduce(
                    _viewState.value,
                    CurrentWeatherAction.CurrentWeatherLoaded(
                        newCurrentWeatherData = weatherData
                    )
                )
            } catch (e: Exception) {
                _viewState.value = reducer.reduce(
                    _viewState.value,
                    CurrentWeatherAction.CurrentWeatherFailed(e.localizedMessage)
                )
            }
        }
    }

}