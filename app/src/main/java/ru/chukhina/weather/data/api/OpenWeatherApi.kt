package ru.chukhina.weather.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.chukhina.weather.data.model.details.WeatherResponse

interface OpenWeatherApi {

    @GET("weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): WeatherResponse

    @GET("weather")
    suspend fun getWeatherByName(@Query("q") name: String): WeatherResponse

    @GET("weather")
    suspend fun getWeatherById(@Query("id") id: Int): WeatherResponse
}
