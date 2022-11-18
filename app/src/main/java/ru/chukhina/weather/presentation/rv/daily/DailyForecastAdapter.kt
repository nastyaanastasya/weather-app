package ru.chukhina.weather.presentation.rv.daily

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.chukhina.weather.domain.model.common.WeatherSimpleDaily

class DailyForecastAdapter(
    private val list: List<WeatherSimpleDaily>
) : RecyclerView.Adapter<DailyForecastHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastHolder =
        DailyForecastHolder.create(parent)

    override fun onBindViewHolder(holder: DailyForecastHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}
