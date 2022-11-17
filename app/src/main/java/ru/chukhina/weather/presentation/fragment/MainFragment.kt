package ru.chukhina.weather.presentation.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ru.chukhina.weather.R
import ru.chukhina.weather.databinding.FragmentMainBinding
import ru.chukhina.weather.domain.model.WeatherDetails
import ru.chukhina.weather.domain.model.WeatherForecast
import ru.chukhina.weather.domain.model.common.WeatherSimpleDaily
import ru.chukhina.weather.domain.model.common.WeatherSimpleHourly
import ru.chukhina.weather.presentation.extensions.hideLoading
import ru.chukhina.weather.presentation.extensions.showLoading
import ru.chukhina.weather.presentation.rv.daily.DailyForecastAdapter
import ru.chukhina.weather.presentation.rv.hourly.HourlyForecastAdapter
import ru.chukhina.weather.presentation.viewmodel.MainViewModel


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val cityId by lazy {
        val args: MainFragmentArgs by navArgs()
        args.cityId
    }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            viewModel.getLocation()
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        loadData(true)

        initObservers()
        initListeners()

        if (cityId != -1) {
            viewModel.getWeather(cityId)
            setShowIconsState(false)
        } else {
            setShowIconsState(true)
        }
    }

    override fun onResume() {
        super.onResume()

        if (!checkPermissions()) {
            requestPermissions()
        }
        viewModel.getLocation()
    }

    private fun initListeners() {
        with(binding) {
            iconSearch.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
            }
        }
    }

    private fun initObservers() {
        viewModel.coordinates.observe(viewLifecycleOwner) {
            it?.fold(onSuccess = { coord ->
                viewModel.getWeather(coord)
            }, onFailure = { e ->
                Log.e("COORDINATES", e.message.toString())
            })
        }
        viewModel.weatherData.observe(viewLifecycleOwner) {
            it?.fold(onSuccess = { data ->
                updateUi(data)
            }, onFailure = { e ->
                Log.e("WEATHER", e.message.toString())
            })
        }
        viewModel.weatherForecast.observe(viewLifecycleOwner) {
            it?.fold(onSuccess = { forecast ->
                updateWeatherForecast(forecast)
                loadData(false)
            }, onFailure = { e ->
                Log.e("FORECAST", e.message.toString())
            })
        }
    }

    private fun updateUi(data: WeatherDetails) {
        with(binding) {
            tvAirPressure.text = data.airPressure
            tvCity.text = data.name
            tvDate.text = data.date
            tvFeelsLike.text = data.feelsLike
            tvHumidity.text = data.humidity
            tvMinMaxTemp.text = data.minMaxTemp
            tvTemp.text = data.temp
            tvVisibility.text = data.visibility
            tvWeatherState.text = data.weatherState
            tvWindDir.text = data.windDirection
            tvWindSpeed.text = data.windSpeed
            ivWeatherIcon.load(data.weatherIconUrl)
        }
        viewModel.getWeatherForecast(data.coordinates)
    }

    private fun updateWeatherForecast(forecast: WeatherForecast) {
        showHourlyForecast(forecast.hourlyForecast)
        showDailyForecast(forecast.dailyForecast)
    }

    private fun showDailyForecast(forecast: List<WeatherSimpleDaily>) {
        binding.rvDaily.apply {
            adapter = DailyForecastAdapter(forecast)
        }
    }

    private fun showHourlyForecast(forecast: List<WeatherSimpleHourly>) {
        binding.rvHourly.apply {
            adapter = HourlyForecastAdapter(forecast)
        }
    }

    private fun setShowIconsState(state: Boolean) {
        val isVisible = when (state) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
        with(binding) {
            iconPlace.visibility = isVisible
            iconSearch.visibility = isVisible
        }
    }

    private fun loadData(state: Boolean) {
        with(binding) {
            when (state) {
                true -> {
                    layout.visibility = View.GONE
                    showLoading()
                }
                false -> {
                    layout.visibility = View.VISIBLE
                    hideLoading()
                }
            }
        }
    }

    private fun requestPermissions() {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun checkPermissions(): Boolean {
        activity?.apply {
            return (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED)
        }
        return false
    }
}
