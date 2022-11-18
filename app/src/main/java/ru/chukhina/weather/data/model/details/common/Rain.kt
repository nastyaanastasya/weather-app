package ru.chukhina.weather.data.model.details.common


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val h: Double
)
