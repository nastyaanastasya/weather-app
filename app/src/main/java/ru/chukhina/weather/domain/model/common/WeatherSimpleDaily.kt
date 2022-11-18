package ru.chukhina.weather.domain.model.common

data class WeatherSimpleDaily(
    val minMaxTemp: String,
    val weatherState: String,
    val weekday: String,
    val weatherIconUrl: String
)
