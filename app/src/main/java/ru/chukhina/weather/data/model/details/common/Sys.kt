package ru.chukhina.weather.data.model.details.common

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val pod: String
)
