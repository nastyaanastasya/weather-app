package ru.chukhina.weather.data.converter

import javax.inject.Inject
import ru.chukhina.weather.domain.converter.WindDirectionsConverter

class WindDirectionsConverterImpl @Inject constructor() : WindDirectionsConverter {

    override fun convertWindToString(degree: Int): String {
        return when (degree.toDouble()) {
            in 22.5..67.5 -> WindDirection.NE.direction
            in 67.5..112.5 -> WindDirection.E.direction
            in 112.5..157.5 -> WindDirection.SE.direction
            in 157.5..202.5 -> WindDirection.S.direction
            in 202.5..247.5 -> WindDirection.SW.direction
            in 247.5..292.5 -> WindDirection.W.direction
            in 292.5..337.5 -> WindDirection.NW.direction
            else -> WindDirection.N.direction
        }
    }
}

enum class WindDirection(val direction: String) {
    N("N"),
    NW("NW"),
    W("W"),
    SW("SW"),
    S("S"),
    SE("SE"),
    E("E"),
    NE("NE"),
}
