package ru.chukhina.weather.data.helper

import ru.chukhina.weather.data.converter.data.WeatherState

class WeatherStateHelper(
    private val weatherCode: Int
) {
    fun getWeatherState(): WeatherState {
        return when (weatherCode) {
            in 1..3 -> WeatherState.MAINLY_CLEAR
            in 45..48 -> WeatherState.FOG
            in 51..67 -> WeatherState.RAIN
            in 71..77 -> WeatherState.SNOW
            in 80..82 -> WeatherState.RAIN_SHOWERS
            in 85..86 -> WeatherState.SNOW
            in 95..99 -> WeatherState.THUNDERSTORM
            else -> WeatherState.CLEAR_SKY
        }
    }
}
