package ru.chukhina.weather.data.repository

import javax.inject.Inject
import ru.chukhina.weather.data.api.OpenMeteoApi
import ru.chukhina.weather.data.api.OpenWeatherApi
import ru.chukhina.weather.domain.mapper.WeatherMapper
import ru.chukhina.weather.domain.model.WeatherDetails
import ru.chukhina.weather.domain.model.WeatherForecast
import ru.chukhina.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl @Inject constructor(
    private val openWeatherApi: OpenWeatherApi,
    private val openMeteoApi: OpenMeteoApi,
    private val mapper: WeatherMapper
) : WeatherRepository {

    override suspend fun getWeatherById(id: Int): WeatherDetails =
        mapper.mapWeatherData(openWeatherApi.getWeatherById(id))

    override suspend fun getWeatherByName(name: String): WeatherDetails =
        mapper.mapWeatherData(openWeatherApi.getWeatherByCityName(name))

    override suspend fun getWeatherForecast(lat: Double, lon: Double): WeatherForecast {
        val forecast = openMeteoApi.getWeatherForecast(lat, lon)
        val hourlyForecast = mapper.mapHourlyWeatherForecast(forecast.hourly)
        val dailyForecast = mapper.mapDailyWeatherForecast(forecast.daily)

        return WeatherForecast(hourlyForecast, dailyForecast)
    }
}
