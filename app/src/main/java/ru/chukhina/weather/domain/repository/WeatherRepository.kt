package ru.chukhina.weather.domain.repository

import ru.chukhina.weather.domain.model.WeatherDetails
import ru.chukhina.weather.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getWeatherById(id: Int): WeatherDetails
    suspend fun getWeatherByCoordinates(lat: Double, lon: Double): WeatherDetails
    suspend fun getCityIdByName(name: String): Int
    suspend fun getWeatherForecast(lat: Double, lon: Double): WeatherForecast
}
