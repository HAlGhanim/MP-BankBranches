package com.example.mp_bankbranches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mp_bankbranches.ui.composables.AppNavigation
import com.example.mp_bankbranches.ui.theme.MPBankBranchesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MPBankBranchesTheme {
                AppNavigation()
            }
        }
    }
}
