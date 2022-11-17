package ru.chukhina.weather.data.helper

import java.util.Calendar

class CurrentTimeHelper {
    fun getCurrentTimeOffset() = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
}
