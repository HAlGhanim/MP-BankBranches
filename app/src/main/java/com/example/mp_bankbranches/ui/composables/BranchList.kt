package com.example.mp_bankbranches.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mp_bankbranches.models.BranchType
import com.example.mp_bankbranches.viewmodel.BranchViewModel

@Composable
fun BranchList(
    onBranchClick: (Int) -> Unit,
    viewModel: BranchViewModel = viewModel()
) {
    val branches by viewModel.filteredBranches
    val favoriteId by viewModel.favoriteBranchId.collectAsState()
    val searchQuery by viewModel.searchQuery
    val isSorted by viewModel.isSorted
    val isFiltered by viewModel.isFiltered
    val selectedBranchType by viewModel.selectedBranchType

    val allTypes = BranchType.entries
    val typeOptions = listOf("All") + allTypes.map { it.name.capitalize() }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Bank Branches", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        SearchBar(query = searchQuery, onQueryChange = { viewModel.updateSearchQuery(it) })
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { viewModel.toggleSort() }) {
                Text(if (isSorted) "Unsort" else "Sort A-Z")
            }

            Button(onClick = { viewModel.toggleFilter() }) {
                Text(if (isFiltered) "Clear Filter" else "Filter 9AM-5PM")
            }

            Box {
                Button(onClick = { expanded = true }) {
                    Text(selectedBranchType?.name?.capitalize() ?: "All Types")
                }

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    typeOptions.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                val type = allTypes.find { it.name.equals(label, ignoreCase = true) }
                                viewModel.selectBranchType(type)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(branches) { branch ->
                BranchCard(
                    branch = branch,
                    isFavorite = branch.id == favoriteId,
                    onClick = { onBranchClick(branch.id) }
                )
            }
        }
    }
}
