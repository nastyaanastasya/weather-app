package ru.chukhina.weather.presentation.helper

import android.view.View

class ViewVisibilityStateHelper {

    fun setVisibility(view: View, state: Boolean) {
        view.visibility = getVisibilityState(state)
    }

    private fun getVisibilityState(state: Boolean) = when (state) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}
