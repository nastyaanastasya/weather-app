package ru.chukhina.weather.domain.converter

interface DateTimeConverter {
    fun convertTimeToDateString(timestamp: Int): String
    fun convertTimeToWeekdayString(timestamp: Int): String
    fun convertTimeToDayTimeString(timestamp: Int): String
}
