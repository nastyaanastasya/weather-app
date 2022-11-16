package ru.chukhina.weather.domain.repository

import ru.chukhina.weather.domain.model.WeatherDetails
import ru.chukhina.weather.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getWeatherById(id: Int): WeatherDetails
    suspend fun getWeatherByName(name: String): WeatherDetails
    suspend fun getWeatherForecast(lat: Double, lon: Double): WeatherForecast
}
