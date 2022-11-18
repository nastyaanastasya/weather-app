package ru.chukhina.weather.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.chukhina.weather.data.converter.CoordinatesConverterImpl
import ru.chukhina.weather.data.converter.DateTimeConverterImpl
import ru.chukhina.weather.data.converter.ImageIconConverterImpl
import ru.chukhina.weather.data.converter.WeatherDataConverterImpl
import ru.chukhina.weather.data.converter.WeatherStateConverterImpl
import ru.chukhina.weather.data.converter.WindDirectionsConverterImpl
import ru.chukhina.weather.domain.converter.CoordinatesConverter
import ru.chukhina.weather.domain.converter.DateTimeConverter
import ru.chukhina.weather.domain.converter.ImageIconConverter
import ru.chukhina.weather.domain.converter.WeatherDataConverter
import ru.chukhina.weather.domain.converter.WeatherStateConverter
import ru.chukhina.weather.domain.converter.WindDirectionsConverter

@Module
@InstallIn(SingletonComponent::class)
interface ConverterModule {

    @Binds
    fun bindsDateTimeConverter(
        impl: DateTimeConverterImpl
    ): DateTimeConverter

    @Binds
    fun bindsCoordinatesConverter(
        impl: CoordinatesConverterImpl
    ): CoordinatesConverter

    @Binds
    fun bindsImageIconConverter(
        impl: ImageIconConverterImpl
    ): ImageIconConverter

    @Binds
    fun bindsWeatherDataConverter(
        impl: WeatherDataConverterImpl
    ): WeatherDataConverter

    @Binds
    fun bindsWeatherStateConverter(
        impl: WeatherStateConverterImpl
    ): WeatherStateConverter

    @Binds
    fun bindsWindDirectionsConverter(
        impl: WindDirectionsConverterImpl
    ): WindDirectionsConverter
}
