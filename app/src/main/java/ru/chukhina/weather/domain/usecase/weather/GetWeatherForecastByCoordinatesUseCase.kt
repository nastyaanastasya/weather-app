package ru.chukhina.weather.domain.usecase.weather

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.chukhina.weather.domain.model.WeatherForecast
import ru.chukhina.weather.domain.model.common.Coordinates
import ru.chukhina.weather.domain.repository.WeatherRepository

class GetWeatherForecastByCoordinatesUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(coordinates: Coordinates): WeatherForecast {
        return withContext(dispatcher) {
            weatherRepository.getWeatherForecast(coordinates.lat, coordinates.lon)
        }
    }
}
