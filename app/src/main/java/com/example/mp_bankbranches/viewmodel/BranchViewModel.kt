package com.example.mp_bankbranches.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mp_bankbranches.models.Branch
import com.example.mp_bankbranches.models.BranchType
import com.example.mp_bankbranches.repository.BranchRepository
import com.example.mp_bankbranches.utils.LocationUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class BranchViewModel : ViewModel() {

    private val _branches = MutableStateFlow(BranchRepository.branches)
    val branches: StateFlow<List<Branch>> = _branches

    private val _favoriteBranchId = MutableStateFlow<Int?>(null)
    val favoriteBranchId: StateFlow<Int?> = _favoriteBranchId

    private val _resolvedAddresses = MutableStateFlow<Map<Int, String>>(emptyMap())
    val resolvedAddresses: StateFlow<Map<Int, String>> = _resolvedAddresses

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _isSorted = mutableStateOf(false)
    val isSorted: State<Boolean> = _isSorted

    private val _selectedBranchType = mutableStateOf<BranchType?>(null)
    val selectedBranchType: State<BranchType?> = _selectedBranchType

    private val _fromHour = mutableStateOf<Float?>(null)
    private val _toHour = mutableStateOf<Float?>(null)
    val fromHour: State<Float?> = _fromHour
    val toHour: State<Float?> = _toHour

    val filteredBranches: State<List<Branch>> = derivedStateOf {
        var result = branches.value

        if (_searchQuery.value.isNotBlank()) {
            result = result.filter {
                it.name.contains(_searchQuery.value, ignoreCase = true) ||
                        it.address.contains(_searchQuery.value, ignoreCase = true)
            }
        }

        if (_fromHour.value != null && _toHour.value != null) {
            val from = _fromHour.value!!
            val to = _toHour.value!!
            result = result.filter {
                val hours = parseHours(it.hours)
                hours != null && hours.second > from && hours.first < to
            }
        }

        _selectedBranchType.value?.let { type ->
            result = result.filter { it.type == type }
        }

        if (_isSorted.value) {
            result = result.sortedBy { it.name }
        }

        result
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleSort() {
        _isSorted.value = !_isSorted.value
    }

    fun selectBranchType(type: BranchType?) {
        _selectedBranchType.value = type
    }

    fun setHourRange(from: Float?, to: Float?) {
        _fromHour.value = from
        _toHour.value = to
    }

    fun setFavoriteBranch(id: Int) {
        _favoriteBranchId.value = id
    }

    fun resolveAddress(context: Context, id: Int, locationUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val coords = LocationUtils.extractLatLngFromUrl(locationUrl)
            if (coords != null) {
                val address = LocationUtils.getAddressFromCoordinates(context, coords.first, coords.second)
                _resolvedAddresses.value += (id to address)
            }
        }
    }

    private fun parseHours(hoursStr: String): Pair<Float, Float>? {
        return try {
            if (hoursStr.contains("24/7", ignoreCase = true)) return Pair(0f, 24f)

            val parts = hoursStr
                .lowercase(Locale.getDefault())
                .replace("am", "")
                .replace("pm", "")
                .split("-", limit = 2)
                .map { it.trim() }

            if (parts.size != 2) return null

            val start = parts[0].replace(":", ".").toFloatOrNull() ?: return null
            val end = parts[1].replace(":", ".").toFloatOrNull() ?: return null

            Pair(start, end)
        } catch (e: Exception) {
            null
        }
    }
}
