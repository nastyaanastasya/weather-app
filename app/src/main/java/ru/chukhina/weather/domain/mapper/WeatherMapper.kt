package ru.chukhina.weather.domain.mapper

import ru.chukhina.weather.data.model.details.WeatherResponse
import ru.chukhina.weather.data.model.forecast.common.Daily
import ru.chukhina.weather.data.model.forecast.common.Hourly
import ru.chukhina.weather.domain.model.WeatherDetails
import ru.chukhina.weather.domain.model.common.WeatherSimpleDaily
import ru.chukhina.weather.domain.model.common.WeatherSimpleHourly

interface WeatherMapper {
    fun mapWeatherData(data: WeatherResponse): WeatherDetails
    fun mapDailyWeatherForecast(data: Daily): List<WeatherSimpleDaily>
    fun mapHourlyWeatherForecast(data: Hourly): List<WeatherSimpleHourly>
}

