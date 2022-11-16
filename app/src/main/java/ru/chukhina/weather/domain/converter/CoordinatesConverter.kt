package ru.chukhina.weather.domain.converter

import ru.chukhina.weather.domain.model.common.Coordinates

interface CoordinatesConverter {
    fun convertCoordinates(lat: Double, lon: Double): Coordinates
}
