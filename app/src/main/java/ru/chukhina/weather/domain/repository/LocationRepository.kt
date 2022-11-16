package ru.chukhina.weather.domain.repository

import ru.chukhina.weather.domain.model.common.Coordinates

interface LocationRepository {
    suspend fun getCoordinates(): Coordinates
}

