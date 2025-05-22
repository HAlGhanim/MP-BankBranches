package com.example.mp_bankbranches.data

data class GeocodingResponse(
    val results: List<Result>,
    val status: String
)

data class Result(
    val formattedAddress: String
)
