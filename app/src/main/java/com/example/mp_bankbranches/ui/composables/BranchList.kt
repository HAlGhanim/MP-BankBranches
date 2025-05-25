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
    val selectedBranchType by viewModel.selectedBranchType
    val fromHour by viewModel.fromHour
    val toHour by viewModel.toHour

    val allTypes = BranchType.entries
    val typeOptions = listOf("All") + allTypes.map { it.name.lowercase().replaceFirstChar { c -> c.uppercase() } }
    var typeExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("NBK Branches", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        SearchBar(query = searchQuery, onQueryChange = { viewModel.updateSearchQuery(it) })
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { viewModel.toggleSort() }) {
                Text(if (isSorted) "Unsort" else "Sort A-Z")
            }

            Box {
                Button(onClick = { typeExpanded = true }) {
                    Text(selectedBranchType?.name?.lowercase()?.replaceFirstChar { it.uppercase() } ?: "All")
                }

                DropdownMenu(expanded = typeExpanded, onDismissRequest = { typeExpanded = false }) {
                    typeOptions.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                val type = allTypes.find { it.name.equals(label, ignoreCase = true) }
                                viewModel.selectBranchType(type)
                                typeExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TimePicker(
            fromHour = fromHour,
            toHour = toHour,
            onFromChange = { viewModel.setHourRange(it, toHour) },
            onToChange = { viewModel.setHourRange(fromHour, it) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.setHourRange(null, null) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Clear Time Filter")
        }

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
