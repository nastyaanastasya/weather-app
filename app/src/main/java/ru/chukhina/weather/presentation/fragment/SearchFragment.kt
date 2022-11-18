package ru.chukhina.weather.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.chukhina.weather.R
import ru.chukhina.weather.databinding.FragmentSearchBinding
import ru.chukhina.weather.presentation.extension.hideLoading
import ru.chukhina.weather.presentation.extension.navigateBack
import ru.chukhina.weather.presentation.extension.showLoading
import ru.chukhina.weather.presentation.helper.ViewVisibilityStateHelper
import ru.chukhina.weather.presentation.viewmodel.SearchViewModel

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        initObservers()
        initToolbar()
        setSearchViewAction()
    }

    private fun initObservers() {
        viewModel.weatherData.observe(viewLifecycleOwner) {
            it?.fold(onSuccess = { cityId ->
                getWeather(cityId)
                isErrorMessageVisible(false)
            }, onFailure = { e ->
                isErrorMessageVisible(true)
                Log.e("CITY_ID", e.message.toString())
            })
        }
    }

    private fun setSearchViewAction() {
        binding.svSearch.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchCity(query?.trim().toString())
                    showLoading()
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return true
                }
            })
        }
    }

    private fun searchCity(query: String) = viewModel.getWeather(query)

    private fun clearSearchView() {
        binding.svSearch.setQuery("", false)
        hideLoading()
    }

    private fun getWeather(cityId: Int) {
        clearSearchView()
        findNavController().navigate(
            R.id.action_searchFragment_to_mainFragment,
            bundleOf("cityId" to cityId)
        )
    }

    private fun initToolbar() = binding.toolbar.setNavigationOnClickListener { navigateBack() }

    private fun isErrorMessageVisible(state: Boolean) {
        clearSearchView()
        ViewVisibilityStateHelper().setVisibility(
            binding.tvNotFound,
            state
        )
    }
}

