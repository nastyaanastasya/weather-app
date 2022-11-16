package ru.chukhina.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.chukhina.weather.domain.usecase.weather.GetCityIdByNameUseCase

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getWeatherByCityNameUseCase: GetCityIdByNameUseCase
) : ViewModel() {

    private val _weatherData: MutableStateFlow<Result<Int>?> = MutableStateFlow(null)
    val weatherData: StateFlow<Result<Int>?> = _weatherData.asStateFlow()

    fun getWeather(query: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherByCityNameUseCase(query)
            }.onSuccess {
                _weatherData.emit(Result.success(it))
            }.onFailure {
                _weatherData.emit(Result.failure(it))
            }
        }
    }
}
