package com.example.mp_bankbranches.data

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
    DIGITAL, PHYSICAL
}
