package ru.chukhina.weather.data.model.forecast

import com.google.gson.annotations.SerializedName
import ru.chukhina.weather.data.model.forecast.common.Daily
import ru.chukhina.weather.data.model.forecast.common.DailyUnits
import ru.chukhina.weather.data.model.forecast.common.Hourly
import ru.chukhina.weather.data.model.forecast.common.HourlyUnits

data class WeatherForecastResponse(
    @SerializedName("daily")
    val daily: Daily,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnits,
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,
    @SerializedName("hourly")
    val hourly: Hourly,
    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnits,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)
