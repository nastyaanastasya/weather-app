package ru.chukhina.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.chukhina.weather.domain.model.WeatherDetails
import ru.chukhina.weather.domain.model.WeatherForecast
import ru.chukhina.weather.domain.model.common.Coordinates
import ru.chukhina.weather.domain.usecase.weather.GetWeatherByCoordinatesUseCase
import ru.chukhina.weather.domain.usecase.weather.GetWeatherByIdUseCase
import ru.chukhina.weather.domain.usecase.weather.GetWeatherForecastByCoordinatesUseCase

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherByIdUseCase: GetWeatherByIdUseCase,
    private val getWeatherByCoordinatesUseCase: GetWeatherByCoordinatesUseCase,
    private val getWeatherForecastByCoordinatesUseCase: GetWeatherForecastByCoordinatesUseCase
) : ViewModel() {

    private var _weatherForecast: MutableStateFlow<Result<WeatherForecast>?> =
        MutableStateFlow(null)
    val weatherForecast: StateFlow<Result<WeatherForecast>?> = _weatherForecast.asStateFlow()

    private var _weatherData: MutableStateFlow<Result<WeatherDetails>?> = MutableStateFlow(null)
    val weatherData: StateFlow<Result<WeatherDetails>?> = _weatherData.asStateFlow()

    fun getWeather(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherByIdUseCase(id)
            }.onSuccess {
                _weatherData.emit(Result.success(it))
            }.onFailure {
                _weatherData.emit(Result.failure(it))
            }
        }
    }

    fun getWeather(coordinates: Coordinates) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherByCoordinatesUseCase(coordinates)
            }.onSuccess {
                _weatherData.emit(Result.success(it))
            }.onFailure {
                _weatherData.emit(Result.failure(it))
            }
        }
    }

    fun getWeatherForecast(coordinates: Coordinates) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherForecastByCoordinatesUseCase(coordinates)
            }.onSuccess {
                _weatherForecast.emit(Result.success(it))
            }.onFailure {
                _weatherForecast.emit(Result.failure(it))
            }
        }
    }
}
