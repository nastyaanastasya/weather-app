package ru.chukhina.weather.data.converter.data

enum class WeatherState(
    val iconId: String,
    val weatherState: String
) {
    CLEAR_SKY("01d", "Clear sky"),
    MAINLY_CLEAR("02d", "Partly cloudy"),
    FOG("50d", "Fog"),
    RAIN("10d", "Rain"),
    SNOW("13d", "Snow"),
    RAIN_SHOWERS("09d", "Rain showers"),
    THUNDERSTORM("11d", "Thunderstorm")
}
