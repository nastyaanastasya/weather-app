package ru.chukhina.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import ru.chukhina.weather.domain.usecase.weather.GetCityIdByNameUseCase

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getWeatherByCityNameUseCase: GetCityIdByNameUseCase
) : ViewModel() {
    private val _weatherData: SingleLiveEvent<Result<Int>> = SingleLiveEvent()
    val weatherData: SingleLiveEvent<Result<Int>> = _weatherData

    fun getWeather(query: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherByCityNameUseCase(query)
            }.onSuccess {
                _weatherData.value = Result.success(it)
            }.onFailure {
                _weatherData.value = Result.failure(it)
            }
        }
    }
}
