package ru.chukhina.weather.data.repository

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import ru.chukhina.weather.data.exceptions.LocationNotFoundException
import ru.chukhina.weather.domain.model.common.Coordinates
import ru.chukhina.weather.domain.repository.LocationRepository

class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getCoordinates(): Coordinates = suspendCoroutine {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                it.resume(
                    Coordinates(location.latitude, location.longitude)
                )
            } else {
                it.resumeWithException(LocationNotFoundException("Location not found"))
            }
        }
    }
}
