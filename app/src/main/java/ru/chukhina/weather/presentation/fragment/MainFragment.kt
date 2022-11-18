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
import ru.chukhina.weather.presentation.extension.hideLoading
import ru.chukhina.weather.presentation.extension.navigateBack
import ru.chukhina.weather.presentation.extension.showLoading
import ru.chukhina.weather.presentation.helper.ViewVisibilityStateHelper
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

    private var isSearched = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        loadData(true)

        initObservers()
        initListeners()
        initUiState()
    }

    private fun initUiState() {
        if (cityId != -1) {
            viewModel.getWeather(cityId)
            setIsCitySearched(true)
        } else {
            getLocation()
            setIsCitySearched(false)
        }
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

        if (isSearched) {
            setToolbarTitle(data.name)
        }
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

    private fun setIsCitySearched(state: Boolean) {
        with(binding) {
            setViewVisibilityState(appBar, state)
            setViewVisibilityState(iconSearch, !state)
            setViewVisibilityState(iconPlace, !state)
            setViewVisibilityState(tvCity, !state)
        }
        isSearched = true
    }

    private fun setViewVisibilityState(view: View, state: Boolean) =
        ViewVisibilityStateHelper().setVisibility(view, state)

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

    private fun setToolbarTitle(title: String) {
        with(binding) {
            toolbar.title = title
            toolbar.setNavigationOnClickListener {
                navigateBack()
            }
        }
    }

    private fun getLocation() {
        if (!checkPermissions()) {
            requestPermissions()
        }
        viewModel.getLocation()
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
