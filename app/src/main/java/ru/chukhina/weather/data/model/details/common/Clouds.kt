package ru.chukhina.weather.data.model.details.common

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int
)
