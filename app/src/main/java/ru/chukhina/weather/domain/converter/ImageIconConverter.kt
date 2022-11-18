package ru.chukhina.weather.domain.converter

interface ImageIconConverter {
    fun convertImageIconIdToUrl(id: String): String
    fun convertWmoWeatherCodeToIconUrl(code: Int): String
}
