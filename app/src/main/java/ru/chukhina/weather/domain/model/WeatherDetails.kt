package ru.chukhina.weather.domain.model

import ru.chukhina.weather.domain.model.common.Coordinates

data class WeatherDetails(
    val id: Int,
    val name: String,
    val coordinates: Coordinates,
    val temp: String,
    val minMaxTemp: String,
    val weatherState: String,
    val date: String,
    val feelsLike: String,
    val humidity: String,
    val windSpeed: String,
    val windDirection: String,
    val visibility: String,
    val airPressure: String,
    val weatherIconUrl: String
)
