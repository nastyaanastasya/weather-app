package ru.chukhina.weather.data.converter

import javax.inject.Inject
import ru.chukhina.weather.domain.converter.CoordinatesConverter
import ru.chukhina.weather.domain.model.common.Coordinates

class CoordinatesConverterImpl @Inject constructor() : CoordinatesConverter {
    override fun convertCoordinates(lat: Double, lon: Double): Coordinates = Coordinates(lat, lon)
}
