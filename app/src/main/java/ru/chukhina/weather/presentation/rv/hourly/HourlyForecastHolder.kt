package ru.chukhina.weather.presentation.rv.hourly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.chukhina.weather.databinding.ItemDaytimeWeatherBinding
import ru.chukhina.weather.domain.model.common.WeatherSimpleHourly

class HourlyForecastHolder(
    private val binding: ItemDaytimeWeatherBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WeatherSimpleHourly) {
        with(binding) {
            tvTime.text = item.time
            tvTemp.text = item.temp
            ivHourlyIcon.load(item.weatherIconUrl)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = HourlyForecastHolder(
            ItemDaytimeWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
