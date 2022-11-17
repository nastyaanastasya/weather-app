package ru.chukhina.weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import ru.chukhina.weather.domain.model.WeatherDetails
import ru.chukhina.weather.domain.model.WeatherForecast
import ru.chukhina.weather.domain.model.common.Coordinates
import ru.chukhina.weather.domain.usecase.location.GetLocationUseCase
import ru.chukhina.weather.domain.usecase.weather.GetWeatherByCoordinatesUseCase
import ru.chukhina.weather.domain.usecase.weather.GetWeatherByIdUseCase
import ru.chukhina.weather.domain.usecase.weather.GetWeatherForecastByCoordinatesUseCase

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val getWeatherByIdUseCase: GetWeatherByIdUseCase,
    private val getWeatherByCoordinatesUseCase: GetWeatherByCoordinatesUseCase,
    private val getWeatherForecastByCoordinatesUseCase: GetWeatherForecastByCoordinatesUseCase
) : ViewModel() {

    private var _weatherForecast: MutableLiveData<Result<WeatherForecast>> =
        MutableLiveData()
    val weatherForecast: LiveData<Result<WeatherForecast>?> = _weatherForecast

    private var _weatherData: MutableLiveData<Result<WeatherDetails>> = MutableLiveData()
    val weatherData: LiveData<Result<WeatherDetails>?> = _weatherData

    private var _coordinates: MutableLiveData<Result<Coordinates>> = MutableLiveData()
    val coordinates: LiveData<Result<Coordinates>?> = _coordinates

    fun getLocation() {
        viewModelScope.launch {
            kotlin.runCatching {
                getLocationUseCase()
            }.onSuccess {
                _coordinates.value = Result.success(it)
            }.onFailure {
                _coordinates.value = Result.failure(it)
            }
        }
    }

    fun getWeather(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherByIdUseCase(id)
            }.onSuccess {
                _weatherData.value = Result.success(it)
            }.onFailure {
                _weatherData.value = Result.failure(it)
            }
        }
    }

    fun getWeather(coordinates: Coordinates) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherByCoordinatesUseCase(coordinates)
            }.onSuccess {
                _weatherData.value = Result.success(it)
            }.onFailure {
                _weatherData.value = Result.failure(it)
            }
        }
    }

    fun getWeatherForecast(coordinates: Coordinates) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherForecastByCoordinatesUseCase(coordinates)
            }.onSuccess {
                _weatherForecast.value = Result.success(it)
            }.onFailure {
                _weatherForecast.value = Result.failure(it)
            }
        }
    }
}
