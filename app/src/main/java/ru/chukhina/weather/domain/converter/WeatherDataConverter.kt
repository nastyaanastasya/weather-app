package ru.chukhina.weather.domain.converter

interface WeatherDataConverter {
    fun convertHumidityToString(humidity: Int): String
    fun convertTempToString(temp: Double): String
    fun convertAirPressureToString(pressure: Int): String
    fun convertVisibilityToString(visibility: Int): String
    fun convertWindSpeedToString(speed: Double): String
    fun convertMinMaxTempToString(minTemp: Double, maxTemp:Double): String
    fun convertWeatherStateToString(desc: String): String
}
