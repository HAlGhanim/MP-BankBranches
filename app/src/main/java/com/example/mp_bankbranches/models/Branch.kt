package com.example.mp_bankbranches.models

data class Branch(
    val id: Int,
    val name: String,
    val type: BranchType,
    val address: String,
    val phone: String,
    val hours: String,
    val location: String,
    val imageUri: String,
)

enum class BranchType {
    HEADQUARTERS,
    ATM,
    BRANCH
}
