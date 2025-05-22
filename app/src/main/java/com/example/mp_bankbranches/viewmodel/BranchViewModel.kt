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

    private val _isFiltered = mutableStateOf(false)
    val isFiltered: State<Boolean> = _isFiltered

    private val _selectedBranchType = mutableStateOf<BranchType?>(null)
    val selectedBranchType: State<BranchType?> = _selectedBranchType

    val filteredBranches: State<List<Branch>> = derivedStateOf {
        var result = branches.value

        if (_searchQuery.value.isNotBlank()) {
            result = result.filter {
                it.name.contains(_searchQuery.value, ignoreCase = true) ||
                        it.address.contains(_searchQuery.value, ignoreCase = true)
            }
        }

        if (_isFiltered.value) {
            result = result.filter { it.hours.contains("9AM - 5PM") }
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

    fun toggleFilter() {
        _isFiltered.value = !_isFiltered.value
    }

    fun selectBranchType(type: BranchType?) {
        _selectedBranchType.value = type
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
}
