package ru.chukhina.weather.domain.usecase.weather

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.chukhina.weather.domain.model.WeatherDetails
import ru.chukhina.weather.domain.model.common.Coordinates
import ru.chukhina.weather.domain.repository.WeatherRepository

class GetWeatherByCoordinatesUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(coordinates: Coordinates): WeatherDetails {
        return withContext(dispatcher) {
            weatherRepository.getWeatherByCoordinates(coordinates.lat, coordinates.lon)
        }
    }
}
