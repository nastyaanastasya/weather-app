package ru.chukhina.weather.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.chukhina.weather.data.model.forecast.WeatherForecastResponse

interface OpenMeteoApi {

    @GET("forecast?hourly=temperature_2m,weathercode&daily=temperature_2m_max,temperature_2m_min,weathercode")
    suspend fun getWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): WeatherForecastResponse
}
