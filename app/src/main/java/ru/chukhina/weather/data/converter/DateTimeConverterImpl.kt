package ru.chukhina.weather.data.converter

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import ru.chukhina.weather.domain.converter.DateTimeConverter

class DateTimeConverterImpl @Inject constructor() : DateTimeConverter {

    override fun convertTimeToDateString(timestamp: Int): String {
        val date = SimpleDateFormat("MMM dd EEE").format(Date(timestamp.toLong()))
        if (date.substring(5, 6) == "0") {
            date.replace("0", "")
        }
        return date
    }

    override fun convertTimeToWeekdayString(timestamp: Int): String =
        SimpleDateFormat("EEE").format(Date(timestamp.toLong()))

    override fun convertTimeToDayTimeString(timestamp: Int): String =
        SimpleDateFormat("hh:mm").format(Date(timestamp.toLong()))
}

