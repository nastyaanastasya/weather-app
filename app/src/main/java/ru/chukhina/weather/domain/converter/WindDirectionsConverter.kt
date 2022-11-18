package ru.chukhina.weather.domain.converter

interface WindDirectionsConverter {
    fun convertWindToString(degree: Int): String
}
