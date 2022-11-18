package ru.chukhina.weather.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.chukhina.weather.data.repository.LocationRepositoryImpl
import ru.chukhina.weather.data.repository.WeatherRepositoryImpl
import ru.chukhina.weather.domain.repository.LocationRepository
import ru.chukhina.weather.domain.repository.WeatherRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepoModule {

    @Binds
    fun bindsLocationRepository(
        impl: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    fun bindsWeatherRepository(
        impl: WeatherRepositoryImpl
    ): WeatherRepository
}
