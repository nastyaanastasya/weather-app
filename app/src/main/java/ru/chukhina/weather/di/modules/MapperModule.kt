package ru.chukhina.weather.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.chukhina.weather.data.mapper.WeatherMapperImpl
import ru.chukhina.weather.domain.mapper.WeatherMapper

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindsWeatherMapper(
        impl: WeatherMapperImpl
    ): WeatherMapper
}
