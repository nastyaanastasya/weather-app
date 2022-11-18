package ru.chukhina.weather.domain.model.common

data class WeatherSimpleHourly(
    val temp: String,
    val time: String,
    val weatherIconUrl: String
)
