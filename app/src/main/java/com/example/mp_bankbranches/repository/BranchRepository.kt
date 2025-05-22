package com.example.mp_bankbranches.repository

import com.example.mp_bankbranches.models.Branch
import com.example.mp_bankbranches.models.BranchType

object BranchRepository {
    val branches = listOf(
        Branch(
            id = 1,
            name = "Downtown Branch",
            type = BranchType.BRANCH,
            address = "123 Main St",
            phone = "123-456-7890",
            hours = "9am - 5pm",
            location = "https://www.google.com/maps?q=37.7749,-122.4194",
            imageUri = "https://via.placeholder.com/150"
        ),
        Branch(
            id = 2,
            name = "City ATM",
            type = BranchType.ATM,
            address = "456 Side St",
            phone = "987-654-3210",
            hours = "24/7",
            location = "https://www.google.com/maps?q=34.0522,-118.2437",
            imageUri = ""
        )
    )
}