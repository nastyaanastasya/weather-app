package ru.chukhina.weather.data.converter

import javax.inject.Inject
import ru.chukhina.weather.data.helper.WeatherStateHelper
import ru.chukhina.weather.domain.converter.WeatherStateConverter

class WeatherStateConverterImpl @Inject constructor(): WeatherStateConverter {

    override fun convertWmoCodeToWeatherStateString(code: Int): String =
        WeatherStateHelper(code).getWeatherState().weatherState
}
