package ru.chukhina.weather.domain.model

import ru.chukhina.weather.domain.model.common.WeatherSimpleDaily
import ru.chukhina.weather.domain.model.common.WeatherSimpleHourly

data class WeatherForecast(
    val hourlyForecast: List<WeatherSimpleHourly>,
    val dailyForecast: List<WeatherSimpleDaily>
)
