package ru.chukhina.weather.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.chukhina.weather.data.model.details.WeatherResponse

interface OpenWeatherApi {

    @GET("weather")
    suspend fun getWeatherByCityName(@Query("q") name: String): WeatherResponse

    @GET("weather")
    suspend fun getWeatherById(@Query("id") id: Int): WeatherResponse
}
