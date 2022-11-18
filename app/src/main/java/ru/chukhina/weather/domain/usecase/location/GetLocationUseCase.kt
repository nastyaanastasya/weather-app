package ru.chukhina.weather.domain.usecase.location

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.chukhina.weather.domain.model.common.Coordinates
import ru.chukhina.weather.domain.repository.LocationRepository

class GetLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Coordinates {
        return withContext(dispatcher) {
            locationRepository.getCoordinates()
        }
    }
}
