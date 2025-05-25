package com.example.mp_bankbranches.utils

import android.content.Context
import android.location.Geocoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object LocationUtils {

    fun extractLatLngFromUrl(url: String): Pair<Double, Double>? {
        val regex = Regex("@([-\\d.]+),([-\\d.]+)")
        val match = regex.find(url)

        return match?.groupValues?.let {
            val lat = it.getOrNull(1)?.toDoubleOrNull()
            val lng = it.getOrNull(2)?.toDoubleOrNull()
            if (lat != null && lng != null) Pair(lat, lng) else null
        }
    }


    suspend fun getAddressFromCoordinates(context: Context, lat: Double, lng: Double): String {
        return withContext(Dispatchers.IO) {
            try {
                val geocoder = Geocoder(context, Locale.getDefault())
                val results = geocoder.getFromLocation(lat, lng, 1)
                results?.firstOrNull()?.getAddressLine(0) ?: "Address not found"
            } catch (e: Exception) {
                "Error resolving address"
            }
        }
    }
}
