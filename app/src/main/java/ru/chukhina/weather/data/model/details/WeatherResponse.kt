package ru.chukhina.weather.data.model.details

import com.google.gson.annotations.SerializedName
import ru.chukhina.weather.data.model.details.common.Clouds
import ru.chukhina.weather.data.model.details.common.Coord
import ru.chukhina.weather.data.model.details.common.Main
import ru.chukhina.weather.data.model.details.common.Rain
import ru.chukhina.weather.data.model.details.common.Sys
import ru.chukhina.weather.data.model.details.common.Weather
import ru.chukhina.weather.data.model.details.common.Wind

data class WeatherResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("rain")
    val rain: Rain,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
)
