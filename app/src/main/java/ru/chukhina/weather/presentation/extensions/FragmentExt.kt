package ru.chukhina.weather.presentation.extensions

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.chukhina.weather.R
import ru.chukhina.weather.databinding.FragmentMainBinding
import ru.chukhina.weather.presentation.MainActivity

fun Fragment.navigateBack() {
    findNavController().popBackStack()
}

fun Fragment.showLoading() {
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
}

fun Fragment.hideLoading() {
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
}
