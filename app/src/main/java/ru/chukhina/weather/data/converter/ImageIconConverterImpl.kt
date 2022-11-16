package ru.chukhina.weather.data.converter

import javax.inject.Inject
import ru.chukhina.weather.data.helper.WeatherStateHelper
import ru.chukhina.weather.domain.converter.ImageIconConverter

private const val BASE_IMAGE_URL = "http://openweathermap.org/img/wn/"
private const val IMAGE_URL_SUFFIX = "@2x.png"

class ImageIconConverterImpl @Inject constructor() : ImageIconConverter {

    override fun convertImageIconIdToUrl(id: String): String =
        BASE_IMAGE_URL + id + IMAGE_URL_SUFFIX

    override fun convertWmoWeatherCodeToIconUrl(code: Int): String =
        convertImageIconIdToUrl(
            WeatherStateHelper(code).getWeatherState().iconId
        )

}
