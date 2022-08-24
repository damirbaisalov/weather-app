package com.example.weatherapp.presentation.screens.second_screen

import com.example.weatherapp.redux.Reducer

class CurrentWeatherReducer: Reducer<CurrentWeatherViewState, CurrentWeatherAction> {

    override fun reduce(
        currentState: CurrentWeatherViewState,
        action: CurrentWeatherAction
    ): CurrentWeatherViewState {

        return when(action) {
            is CurrentWeatherAction.CurrentWeatherLoading -> {
                currentState.copy(
                    showLoading = true
                )
            }
            is CurrentWeatherAction.CurrentWeatherLoaded -> {
                currentState.copy(
                    weatherCurrentData = action.newCurrentWeatherData,
                    showLoading = false
                )
            }
            is CurrentWeatherAction.CurrentWeatherFailed -> {
                currentState.copy(
                    showLoading = false,
                    error = action.error
                )
            }
            else -> currentState
        }
    }
}