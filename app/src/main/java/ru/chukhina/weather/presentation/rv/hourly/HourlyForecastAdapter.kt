package ru.chukhina.weather.presentation.rv.hourly

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.chukhina.weather.domain.model.common.WeatherSimpleHourly

class HourlyForecastAdapter(
    private val list: List<WeatherSimpleHourly>
) : RecyclerView.Adapter<HourlyForecastHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastHolder =
        HourlyForecastHolder.create(parent)

    override fun onBindViewHolder(holder: HourlyForecastHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}
