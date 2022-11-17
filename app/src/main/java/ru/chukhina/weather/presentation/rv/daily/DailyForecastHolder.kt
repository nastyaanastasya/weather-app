package ru.chukhina.weather.presentation.rv.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.chukhina.weather.databinding.ItemWeekdayWeatherBinding
import ru.chukhina.weather.domain.model.common.WeatherSimpleDaily

class DailyForecastHolder(
    private val binding: ItemWeekdayWeatherBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WeatherSimpleDaily) {
        with(binding) {
            tvWeekday.text = item.weekday
            tvWeatherState.text = item.weatherState
            tvMinMaxTemp.text = item.minMaxTemp
            ivDailyIcon.load(item.weatherIconUrl)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = DailyForecastHolder(
            ItemWeekdayWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
