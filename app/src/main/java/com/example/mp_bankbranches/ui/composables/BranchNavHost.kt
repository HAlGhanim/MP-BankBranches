package com.example.mp_bankbranches.ui.composables

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mp_bankbranches.viewmodel.BranchViewModel

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val viewModel: BranchViewModel = viewModel()

    NavHost(navController = navController, startDestination = "branchList") {
        composable("branchList") {
            BranchList(onBranchClick = { id ->
                navController.navigate("branchDetail/$id")
            }, viewModel = viewModel)
        }
        composable(
            route = "branchDetail/{branchId}",
            arguments = listOf(navArgument("branchId") { type = NavType.IntType })
        ) { backStackEntry ->
            val branchId = backStackEntry.arguments?.getInt("branchId") ?: return@composable
            BranchDetails(
                branchId = branchId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}