package ru.chukhina.weather.data.mapper

import javax.inject.Inject
import ru.chukhina.weather.data.helper.CurrentTimeHelper
import ru.chukhina.weather.data.model.details.WeatherResponse
import ru.chukhina.weather.data.model.forecast.common.Daily
import ru.chukhina.weather.data.model.forecast.common.Hourly
import ru.chukhina.weather.domain.converter.CoordinatesConverter
import ru.chukhina.weather.domain.converter.DateTimeConverter
import ru.chukhina.weather.domain.converter.ImageIconConverter
import ru.chukhina.weather.domain.converter.WeatherDataConverter
import ru.chukhina.weather.domain.converter.WeatherStateConverter
import ru.chukhina.weather.domain.converter.WindDirectionsConverter
import ru.chukhina.weather.domain.mapper.WeatherMapper
import ru.chukhina.weather.domain.model.WeatherDetails
import ru.chukhina.weather.domain.model.common.WeatherSimpleDaily
import ru.chukhina.weather.domain.model.common.WeatherSimpleHourly

private const val HOURS_IN_A_DAY = 24
private const val DAYS_IN_A_WEEK = 7

private const val WEEKDAY_TODAY_DESC = "Today"

class WeatherMapperImpl @Inject constructor(
    private val coordinatesConverter: CoordinatesConverter,
    private val dateTimeConverter: DateTimeConverter,
    private val imageIconConverter: ImageIconConverter,
    private val weatherDataConverter: WeatherDataConverter,
    private val weatherStateConverter: WeatherStateConverter,
    private val windDirectionsConverter: WindDirectionsConverter
) : WeatherMapper {

    override fun mapWeatherData(data: WeatherResponse): WeatherDetails = WeatherDetails(
        data.id,
        data.name,
        coordinatesConverter.convertCoordinates(data.coord.lat, data.coord.lon),
        weatherDataConverter.convertTempToString(data.main.temp),
        weatherDataConverter.convertMinMaxTempToString(data.main.tempMin, data.main.tempMax),
        weatherDataConverter.convertWeatherStateToString(data.weather[0].description),
        dateTimeConverter.convertTimeToDateString(data.dt),
        weatherDataConverter.convertTempToString(data.main.feelsLike),
        weatherDataConverter.convertHumidityToString(data.main.humidity),
        weatherDataConverter.convertWindSpeedToString(data.wind.speed),
        windDirectionsConverter.convertWindToString(data.wind.deg),
        weatherDataConverter.convertVisibilityToString(data.visibility),
        weatherDataConverter.convertAirPressureToString(data.main.pressure),
        imageIconConverter.convertImageIconIdToUrl(data.weather[0].icon)
    )

    override fun mapDailyWeatherForecast(data: Daily): List<WeatherSimpleDaily> {
        val list = mutableListOf<WeatherSimpleDaily>()
        for (i in 0 until DAYS_IN_A_WEEK) {
            val minMaxTemp = weatherDataConverter.convertMinMaxTempToString(
                data.temperature2mMin[i],
                data.temperature2mMax[i]
            )
            val weatherState =
                weatherStateConverter.convertWmoCodeToWeatherStateString(data.weathercode[i])
            val weekday = when (i) {
                0 -> WEEKDAY_TODAY_DESC
                else -> dateTimeConverter.convertTimeToWeekdayString(data.time[i])
            }
            val iconUrl = imageIconConverter.convertWmoWeatherCodeToIconUrl(data.weathercode[i])

            list.add(
                WeatherSimpleDaily(
                    minMaxTemp,
                    weatherState,
                    weekday,
                    iconUrl
                )
            )
        }
        return list
    }

    override fun mapHourlyWeatherForecast(data: Hourly): List<WeatherSimpleHourly> {
        val offset = CurrentTimeHelper().getCurrentTimeOffset() + 1

        val list = mutableListOf<WeatherSimpleHourly>()
        for (i in offset until HOURS_IN_A_DAY + offset) {
            list.add(
                WeatherSimpleHourly(
                    weatherDataConverter.convertTempToString(data.temperature2m[i]),
                    dateTimeConverter.convertTimeToDayTimeString(data.time[i]),
                    imageIconConverter.convertWmoWeatherCodeToIconUrl(data.weathercode[i])
                )
            )
        }
        return list
    }
}
