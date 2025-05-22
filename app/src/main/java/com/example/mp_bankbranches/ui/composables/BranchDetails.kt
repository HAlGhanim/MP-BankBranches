package com.example.mp_bankbranches.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.mp_bankbranches.R
import com.example.mp_bankbranches.viewmodel.BranchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchDetails(
    branchId: Int,
    viewModel: BranchViewModel,
    onBack: () -> Unit
) {
    val branches by viewModel.branches.collectAsState()
    val resolvedAddresses by viewModel.resolvedAddresses.collectAsState()
    val favoriteId by viewModel.favoriteBranchId.collectAsState()
    val branch = branches.find { it.id == branchId } ?: return

    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(branch.id) {
        viewModel.resolveAddress(context, branch.id, branch.location)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(branch.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            if (branch.imageUri.isNotEmpty()) {
                AsyncImage(
                    model = branch.imageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_default_branch),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Type: ${branch.type}")
            Text("Address: ${resolvedAddresses[branch.id] ?: branch.address}")
            Text("Phone: ${branch.phone}")
            Text("Hours: ${branch.hours}")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { uriHandler.openUri(branch.location) }) {
                Text("Open in Maps")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.setFavoriteBranch(branch.id) },
                enabled = favoriteId != branch.id,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (favoriteId == branch.id) Color(0xFFFFF8E1) else MaterialTheme.colorScheme.primary
                )
            ) {
                Text(if (favoriteId == branch.id) "🌟 Favorite Branch" else "Set as Favorite")
            }
        }
    }
}
