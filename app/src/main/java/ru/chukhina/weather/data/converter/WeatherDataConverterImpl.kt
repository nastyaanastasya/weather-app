package ru.chukhina.weather.data.converter

import javax.inject.Inject
import ru.chukhina.weather.domain.converter.WeatherDataConverter

private const val DEGREE_UNITS = "\u2103"
private const val HUMIDITY_UNITS = "\u0025"
private const val AIR_PRESSURE_UNITS = "hPa"
private const val DISTANCE_UNITS = "km"
private const val WIND_SPEED_UNITS = "km/h"

private const val SPEED_UNITS_MULTIPLIER = 3.6
private const val DISTANCE_UNITS_MULTIPLIER = 10e-3

class WeatherDataConverterImpl @Inject constructor() : WeatherDataConverter {

    override fun convertHumidityToString(humidity: Int): String = "${humidity}${HUMIDITY_UNITS}"

    override fun convertTempToString(temp: Double): String = "${temp.toInt()}${DEGREE_UNITS}"

    override fun convertAirPressureToString(pressure: Int): String =
        "$pressure $AIR_PRESSURE_UNITS"

    override fun convertVisibilityToString(visibility: Int): String =
        "${visibility * DISTANCE_UNITS_MULTIPLIER} $DISTANCE_UNITS"

    override fun convertWindSpeedToString(speed: Double): String =
        "${(speed * SPEED_UNITS_MULTIPLIER).toInt()} $WIND_SPEED_UNITS"

    override fun convertMinMaxTempToString(minTemp: Double, maxTemp: Double): String =
        "${maxTemp.toInt()}/${minTemp.toInt()}${DEGREE_UNITS}"

    override fun convertWeatherStateToString(desc: String): String =
        desc.replaceFirstChar { it.uppercase() }
}
