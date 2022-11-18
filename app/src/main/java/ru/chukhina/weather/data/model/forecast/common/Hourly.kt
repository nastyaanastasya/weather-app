package ru.chukhina.weather.data.model.forecast.common

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("time")
    val time: List<Int>,
    @SerializedName("weathercode")
    val weathercode: List<Int>
)
