package ru.chukhina.weather.domain.converter

interface WeatherStateConverter {
    fun convertWmoCodeToWeatherStateString(code: Int): String
}
