package ru.chukhina.weather.domain.usecase.weather

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.chukhina.weather.domain.repository.WeatherRepository

class GetCityIdByNameUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(name: String): Int {
        return withContext(dispatcher) {
            weatherRepository.getCityIdByName(name)
        }
    }
}
